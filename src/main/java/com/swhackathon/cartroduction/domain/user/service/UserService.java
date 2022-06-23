package com.swhackathon.cartroduction.domain.user.service;

import com.swhackathon.cartroduction.domain.user.domain.entity.User;
import com.swhackathon.cartroduction.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}
}
