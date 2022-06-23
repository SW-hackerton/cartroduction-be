package com.swhackathon.cartroduction.blockchain;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import com.swhackathon.cartroduction.domain.user.domain.entity.User;
import com.swhackathon.cartroduction.domain.user.domain.repository.UserRepository;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
public class BlockchainServiceTest {

    BlockchainService blockchainService = new BlockchainService();
    Registration reg = new Registration((long)3,"고고유진", Category.엔진오일교체,"b","c", "33삼3333", "235","url1","url2", LocalDateTime.now());
    @Test
    void registTest() throws CipherException, IOException {
        blockchainService.RegistToBC(reg);
    }

    @Test
    void getMaintTest() {
        blockchainService.getMaintenanceListsByCarNumber(reg.getCarNumber());
    }

    @Test
    void getMaintCountTest(){
        blockchainService.getMaintenanceCountByCarNumber(reg.getCarNumber());

    }

}
