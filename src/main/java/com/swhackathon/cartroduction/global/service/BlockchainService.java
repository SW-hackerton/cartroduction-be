package com.swhackathon.cartroduction.global.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlockchainService {

    //스마트 컨트랙트 메소드 사용에 필요한 필수 정보
    String contractAddress = "";
    String contractABI = "";
    String privateKey = "";

    SingleKeyring executor;

    Caver caver = new Caver("https://api.baobab.klaytn.net:8651/");

    //생성자에서 관리자 지갑 추가
    public BlockchainService() {
        System.out.print(privateKey);
        executor = KeyringFactory.createFromPrivateKey(privateKey);
        caver.wallet.add(executor);
    }

    //블록체인에 데이터 등록
    public void RegistToBC(Registration registration) throws CipherException, IOException {

        RepairList repairList = registration.getRepairList();
        long userId =registration.getId();
        String managerName= registration.getManagerName();
        String date=registration.getDate().toString();
        String carNumber= registration.getCarNumber();
        String carDistance=registration.getCarDistance();
        String repairListString=""+repairList.getCategory()+":"+repairList.getContent()+":"+repairList.getPrice();
        String carImgUrl=registration.getCarImageUrl();
        String estimatesImgUrl= registration.getEstimatesImageUrl();

        try{
            Contract contract = caver.contract.create(contractABI, contractAddress);
            System.out.println(1);

            SendOptions sendOptions = new SendOptions();
            sendOptions.setFrom(executor.getAddress());

            sendOptions.setGas(BigInteger.valueOf(4000000));
            TransactionReceipt.TransactionReceiptData receipt = contract.send(sendOptions, "resistMaintenanceData", userId, managerName, date,
                                                                                carNumber, carDistance, repairListString, carImgUrl, estimatesImgUrl);
            System.out.println(receipt);
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
        try{
            Contract contract = caver.contract.create(contractABI, contractAddress);

            for(int i = 0; i < count;i++) {
                List<Type> result = contract.call("getMaintenance", carNumber, i);

                long id = ((Uint256)result.get(0)).getValue().longValue();
                String name = (String)result.get(1).getValue();
                String date = (String)result.get(2).getValue();
                String distance = ((Uint256)result.get(3)).getValue().toString();

                //repairList 만들기
                String repairListStrings[] = ((String)result.get(4).getValue()).split(":", 3);

                RepairList repairList = new RepairList((long)i, Category.valueOf(repairListStrings[0]),repairListStrings[1],repairListStrings[2]);

                String carImgUrl = (String)result.get(5).getValue();
                String estimateImgUrl = (String)result.get(6).getValue();

                Registration reg = new Registration(id,name, repairList, date,distance,estimateImgUrl,carImgUrl, LocalDateTime.parse(date));
                lists.add(reg);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            //handle exception..
            System.out.print("ERROR!!!!!: ");

            System.out.println(e);
        }

        return lists;
    }

    public int getMaintenanceCountByCarNumber(String carNumber) {
        int length = 0;

        try{
            Contract contract = caver.contract.create(contractABI, contractAddress);

            List<Type> result = contract.call("getMaintenanceCount", carNumber);

            length=((Uint256)result.get(0)).getValue().intValue();

        } catch (IOException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            //handle exception..
            System.out.print("ERROR!!!!!: ");

            System.out.println(e);
        }

        return length;
    }

}
