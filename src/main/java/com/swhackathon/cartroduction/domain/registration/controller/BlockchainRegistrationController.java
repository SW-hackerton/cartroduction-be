package com.swhackathon.cartroduction.domain.registration.controller;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationRequest;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationResponse;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.CipherException;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class BlockchainRegistrationController {
    public final BlockchainService blockchainService;

    @PostMapping(value = "/manager/register")
    public ResponseEntity register(
            @Validated @RequestBody RegistrationRequest request) throws CipherException, IOException {
        Registration registration = request.toEntity();

        System.out.println("registration.toString() = " + registration.toString());
        blockchainService.RegistToBC(registration);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<List<Registration>> getMaintenanceByCarNames(@PathVariable("keyword") String keyword) {

        System.out.println(keyword);
        return ResponseEntity.ok()
                .body(blockchainService.getMaintenanceListsByCarNumber(keyword).stream()
                        .map(RegistrationResponse::of)
                        .collect(Collectors.toList()));
    }

}
