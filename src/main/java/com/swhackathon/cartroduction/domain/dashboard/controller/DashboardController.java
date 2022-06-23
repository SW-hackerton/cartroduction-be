package com.swhackathon.cartroduction.domain.dashboard.controller;

import com.swhackathon.cartroduction.domain.dashboard.domain.RepairInfo;
import com.swhackathon.cartroduction.domain.dashboard.dto.DashboardResponse;
import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.service.RegistrationService;
import com.swhackathon.cartroduction.global.service.BlockchainService;
import java.util.List;
import java.util.stream.Collectors;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DashboardController {

//	private final RegistrationService registrationService;
	public final BlockchainService blockchainService;

	@GetMapping("/{carNumber}")
	public ResponseEntity<DashboardResponse> findDashboardsByCarNumber(
		@PathParam("carNumber") String carNumber) {
		List<Registration> registrations = blockchainService.getMaintenanceListsByCarNumber(carNumber);
//		List<Registration> registrations = registrationService.findByCarName(carNumber);
		List<RepairInfo> repairInfo = registrations.stream()
			.map(RepairInfo::of)
			.collect(Collectors.toList());
		return ResponseEntity.ok().body(DashboardResponse.of(repairInfo, registrations));
	}
}
