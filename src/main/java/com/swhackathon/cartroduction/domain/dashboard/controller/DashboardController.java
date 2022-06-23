package com.swhackathon.cartroduction.domain.dashboard.controller;

import com.swhackathon.cartroduction.domain.dashboard.domain.RepairInfo;
import com.swhackathon.cartroduction.domain.dashboard.dto.DashboardResponse;
import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DashboardController {

//	private final RegistrationService registrationService;
	public final BlockchainService blockchainService;

	@GetMapping("/dashboard/{carNumber}")
	public ResponseEntity<DashboardResponse> findDashboardsByCarNumber(
		@PathVariable("carNumber") String carNumber) {
		String decodeKeyword = URLDecoder.decode(carNumber, StandardCharsets.UTF_8);
		List<Registration> registrations = blockchainService.getMaintenanceListsByCarNumber(decodeKeyword);
//		List<Registration> registrations = registrationService.findByCarName(carNumber);
		List<RepairInfo> repairInfo = registrations.stream()
			.map(RepairInfo::of)
			.collect(Collectors.toList());
		return ResponseEntity.ok().body(DashboardResponse.of(repairInfo, registrations));
	}
}
