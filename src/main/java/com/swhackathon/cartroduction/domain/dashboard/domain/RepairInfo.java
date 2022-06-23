package com.swhackathon.cartroduction.domain.dashboard.domain;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RepairInfo {

	private LocalDate date;

	private int carDistance;

	private List<RepairList> repairList;

	public static RepairInfo of(Registration registration) {
		return RepairInfo.builder()
			.date(registration.getDate())
			.carDistance(registration.getCarDistance())
			.repairList(registration.getRepairList())
			.build();
	}
}
