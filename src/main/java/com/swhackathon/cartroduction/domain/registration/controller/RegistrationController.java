package com.swhackathon.cartroduction.domain.registration.controller;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.dto.RegistrationRequest;
import com.swhackathon.cartroduction.domain.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegistrationController {

	public final RegistrationService registrationService;

	@PostMapping("/manager/register")
	public ResponseEntity<Registration> register(@Validated @RequestBody RegistrationRequest request) {
		Registration registration = request.toEntity();
		return ResponseEntity.ok(registrationService.save(registration));
	}
}