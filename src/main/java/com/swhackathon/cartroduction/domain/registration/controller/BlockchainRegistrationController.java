package com.swhackathon.cartroduction.domain.registration.controller;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationRequest;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationResponse;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class BlockchainRegistrationController {

	public final BlockchainService blockchainService;

	@PostMapping("/manager/register")
	public ResponseEntity register(
		@Validated @RequestBody RegistrationRequest request) throws CipherException, IOException {
		Registration registration = request.toEntity();
		blockchainService.RegistToBC(registration);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	@GetMapping("/{keyword}")
	public ResponseEntity<List<Registration>> getMaintenanceByCarNames(
		@PathVariable("keyword") String keyword) {
		String decodeKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
		return ResponseEntity.ok()
			.body(blockchainService.getMaintenanceListsByCarNumber(decodeKeyword).stream()
				.map(RegistrationResponse::of)
				.collect(Collectors.toList()));
	}

}
