package com.swhackathon.cartroduction.domain.registration.controller;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationRequest;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationResponse;
import com.swhackathon.cartroduction.domain.registration.service.RegistrationService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegistrationController {

	public final RegistrationService registrationService;

	@PostMapping("/manager/register")
	public ResponseEntity<Registration> register(
		@Validated @RequestBody RegistrationRequest request) {
		Registration registration = request.toEntity();
		return new ResponseEntity<>(registrationService.save(registration), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<Registration>> findAll() {
		return ResponseEntity.ok()
			.body(registrationService.findAll().stream()
				.map(RegistrationResponse::of)
				.collect(Collectors.toList()));
	}
}
