package com.swhackathon.cartroduction.blockchain;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BlockchainServiceTest {

    BlockchainService blockchainService = new BlockchainService();

    File img = new File("src/main/resources/button.png");
    IpfsService ipfsService = new IpfsService();

    @Test
    void registTest() throws CipherException, IOException {
        RepairList rl = new RepairList(null, Category.엔진오일교체,"b","c");
        List<RepairList> repairLists = new ArrayList<>();
        repairLists.add(rl);
        Registration reg = new Registration((long)1,"고고유진", repairLists, "22이2222", "235","url1","url2", LocalDate.now());



        blockchainService.RegistToBC(reg);
    }

    @Test
    void getMaintTest() {
        RepairList rl = new RepairList(null, Category.엔진오일교체,"b","c");
        List<RepairList> repairLists = new ArrayList<>();
        repairLists.add(rl);
        Registration reg = new Registration(null,"고고유진", repairLists, "22이2222", "235","url1","url2", LocalDate.now());


        blockchainService.getMaintenanceListsByCarNumber(reg.getCarNumber());
    }

    @Test
    void getMaintCountTest(){
        RepairList rl = new RepairList(null, Category.엔진오일교체,"b","c");
        List<RepairList> repairLists = new ArrayList<>();
        repairLists.add(rl);
        Registration reg = new Registration(null,"고고유진", repairLists, "22이2222", "235","url1","url2", LocalDate.now());


        blockchainService.getMaintenanceCountByCarNumber(reg.getCarNumber());

    }

    @Test
    void ipfsUploadUrlTest() throws IOException {
        System.out.println(ipfsService.uploadImg("src/main/resources/button.png"));
    }

    @Test
    void ipfsUploadByteTest() throws IOException {
        BufferedImage bImage = ImageIO.read(img);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", bos);
        byte[] data = bos.toByteArray();
        System.out.println("");
        System.out.print(bos);
        System.out.println("length");
        System.out.println(data.length);
        System.out.print(data);
        System.out.println("");
        System.out.print("이미지 업로드");
        System.out.println(ipfsService.uploadImg(data));

    }

    @Test
    void ipfsDownloadTest() throws IOException {
        //byte[] code = ipfsService.getImg("QmZ7AnUeMdiJsqedw3fx6NLMW24nUsMw67kHRrRx6d9ahw");
        byte[] code = ipfsService.getImg("QmTv3dexGexy7MqnEZNzzxqwmjXh6zudKRt1mSqy9YbSa7");

        String str = new String(code);
        System.out.println(str);
    }

}
