package com.swhackathon.cartroduction.domain.dashboard.dto;

import com.swhackathon.cartroduction.domain.dashboard.domain.RepairInfo;
import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairInfoResponse {

	private RepairInfo repairInfo;

	public static RepairInfoResponse of(Registration registration) {
		final RepairInfo repairInfo = new RepairInfo(
			registration.getDate(),
			registration.getCarDistance(),
			registration.getRepairList());
		return RepairInfoResponse.builder()
			.repairInfo(repairInfo)
			.build();
	}

}
