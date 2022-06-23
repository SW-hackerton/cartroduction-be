package com.swhackathon.cartroduction.global.service;

import static com.swhackathon.cartroduction.global.domain.confidential.ContractInfo.*;

import com.klaytn.caver.Caver;
import com.klaytn.caver.abi.datatypes.Type;
import com.klaytn.caver.abi.datatypes.generated.Uint256;
import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.contract.SendOptions;
import com.klaytn.caver.methods.response.TransactionReceipt;
import com.klaytn.caver.wallet.keyring.KeyringFactory;
import com.klaytn.caver.wallet.keyring.SingleKeyring;
import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BlockchainService {

	private final SingleKeyring executor;
	private final Caver caver = new Caver("https://api.baobab.klaytn.net:8651/");


	//생성자에서 관리자 지갑 추가
	@Autowired
	public BlockchainService() {
		executor = KeyringFactory.createFromPrivateKey(privateKey);
		caver.wallet.add(executor);
	}

	//블록체인에 데이터 등록
	public void RegistToBC(Registration registration) throws CipherException, IOException {

		System.out.println(registration.toString());

		long userId = registration.getId();
		String managerName = registration.getManagerName();
		String date = registration.getDate().toString();
		String carNumber = registration.getCarNumber();
		int carDistance = registration.getCarDistance();

		List<RepairList> repairLists = registration.getRepairList();

		String repairListString = "";

		for(RepairList data : repairLists){
			repairListString += data.getCategory().toString()+":"
					+data.getContent()+":"+data.getPrice()+";";
		}
		String carImgUrl = registration.getCarImageUrl();
		String estimatesImgUrl = registration.getEstimatesImageUrl();

		System.out.println(repairListString+carImgUrl+estimatesImgUrl);


		try {
			Contract contract = caver.contract.create(contractABI, contractAddress);

			SendOptions sendOptions = new SendOptions();
			sendOptions.setFrom(executor.getAddress());

			sendOptions.setGas(BigInteger.valueOf(4000000));
			TransactionReceipt.TransactionReceiptData receipt = contract.send(sendOptions,
				"registMaintenanceData", userId, managerName, date,
				carNumber, (long)carDistance, repairListString, carImgUrl, estimatesImgUrl);
			System.out.println(receipt.getTxError());
		} catch (IOException | TransactionException | ClassNotFoundException | NoSuchMethodException |
			InvocationTargetException | InstantiationException | IllegalAccessException e) {
			//handle exception..
			System.out.print("ERROR!!!!!: ");

			System.out.println(e);
		}
	}

	public List<Registration> getMaintenanceListsByCarNumber(String carNumber) {

		int count = getMaintenanceCountByCarNumber(carNumber);
		List<Registration> lists = new ArrayList<Registration>();
		try {
			Contract contract = caver.contract.create(contractABI, contractAddress);

			for (int i = 0; i < count; i++) {
				List<Type> result = contract.call("getMaintenance", carNumber, i);

				long userId = ((Uint256) result.get(0)).getValue().longValue();
				String name = (String) result.get(1).getValue();
				String date = (String) result.get(2).getValue();
				int distance = ((Uint256)result.get(3)).getValue().intValue();

				//repairList 만들기
				String repairListStrings[] = ((String) result.get(4).getValue()).split(";");
				List<RepairList> repairLists = new ArrayList<>();
				for(String s:repairListStrings) {
					String repair[] = s.split(":",3);
					RepairList rl = new RepairList(null, Category.valueOf(repair[0]), repair[1], Integer.parseInt(repair[2]));

					repairLists.add(rl);
				}

				String carImgUrl = (String) result.get(5).getValue();
				String estimateImgUrl = (String) result.get(6).getValue();

				Registration reg = new Registration(userId, name,
					repairLists, carNumber, distance, estimateImgUrl, carImgUrl,
					LocalDate.parse(date));
				lists.add(reg);
				System.out.println(reg);
			}
		} catch (IOException | ClassNotFoundException | NoSuchMethodException |
			InvocationTargetException | InstantiationException | IllegalAccessException e) {
			//handle exception..
			System.out.print("ERROR!!!!!: ");

			System.out.println(e);
		}

		System.out.println(lists.get(0).toString());

		return lists;
	}

	public int getMaintenanceCountByCarNumber(String carNumber) {
		int length = 0;

		try {
			Contract contract = caver.contract.create(contractABI, contractAddress);

			List<Type> result = contract.call("getMaintenanceCount", carNumber);

			length = ((Uint256) result.get(0)).getValue().intValue();

		} catch (IOException | ClassNotFoundException | NoSuchMethodException |
			InvocationTargetException | InstantiationException | IllegalAccessException e) {
			//handle exception..
			System.out.print("ERROR!!!!!: ");

			System.out.println(e);
		}

		return length;
	}

}
