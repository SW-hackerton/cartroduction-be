package com.swhackathon.cartroduction.domain.user.controller;

import com.swhackathon.cartroduction.domain.user.domain.entity.User;
import com.swhackathon.cartroduction.domain.user.dto.UserRequest;
import com.swhackathon.cartroduction.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping("/")
	public ResponseEntity<User> saveUser(@Validated @RequestBody UserRequest userRequest) {
		User user = userRequest.toEntity();
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
}
