package com.swhackathon.cartroduction.domain.registration.service;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.repository.RegistrationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

	private final RegistrationRepository registrationRepository;

	public Registration save(Registration registration) {
		return registrationRepository.save(registration);
	}

	public List<Registration> findAll() {
		return registrationRepository.findAll();
	}

	public List<Registration> findByCarName(String keyword) {
		return registrationRepository.findByCarNumber(keyword);
	}
}
