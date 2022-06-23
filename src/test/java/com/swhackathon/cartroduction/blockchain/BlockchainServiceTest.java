package com.swhackathon.cartroduction.blockchain;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import com.swhackathon.cartroduction.global.service.IpfsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
public class BlockchainServiceTest {

    BlockchainService blockchainService = new BlockchainService();
    Registration reg = new Registration((long)3,"고고유진", Category.엔진오일교체,"b","c", "33삼3333", "235","url1","url2", LocalDateTime.now());

    File img = new File("src/main/resources/button.png");
    IpfsService ipfsService = new IpfsService();

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

    @Test
    void ipfsUploadTest() throws IOException {
        BufferedImage bImage = ImageIO.read(img);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();
        System.out.print(bos);
        System.out.print(data);
        System.out.print("이미지 업로드");
        System.out.println(ipfsService.uploadImg("src/main/resources/button.png"));

    }

    @Test
    void ipfsDownloadTest() throws IOException {
        byte[] code = ipfsService.getImg("QmZ7AnUeMdiJsqedw3fx6NLMW24nUsMw67kHRrRx6d9ahw");
        String str = new String(code);
        System.out.println(str);
    }

}
