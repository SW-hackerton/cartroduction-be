package com.swhackathon.cartroduction.global.service;

import com.klaytn.caver.Caver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class IpfsService {
    Caver caver = new Caver();



    public void IpfsService() {
        String host = "https://ipfs-gateway.cloud/";
        int port = 5001;
        boolean isSSL = true;
        caver.ipfs.setIPFSNode(host,port,isSSL);
    }

    public String uploadImg(byte[] bytecode) throws IOException {
        System.out.print("in uploadImg");

        System.out.print(bytecode);

        return caver.ipfs.add(bytecode);
    }

    public String uploadImg(String url) throws IOException {

        String host = "ipfs.infura.io";
        int port = 5001;
        boolean isSSL = true;
        caver.ipfs.setIPFSNode(host,port,isSSL);


        System.out.println("in uploadImg");

        System.out.println(url.getBytes());

        String cid = caver.ipfs.add(url.getBytes());

        System.out.println(cid);
        return cid;
    }

    public byte[] getImg(String cid) throws IOException {
        String host = "ipfs.infura.io";
        int port = 5001;
        boolean isSSL = true;
        caver.ipfs.setIPFSNode(host,port,isSSL);

        byte[] content = caver.ipfs.get(cid);

        return content;
    }
}
