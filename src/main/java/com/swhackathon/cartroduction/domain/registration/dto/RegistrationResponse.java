package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {

	private String managerName;

	private RepairList repairList;

	private String carNumber;

	private int carDistance;

	private String estimatesImageUrl;

	private String carImageUrl;

	private LocalDate date;

	public static Registration of(Registration registration) {
		return Registration.builder()
			.id(registration.getId())
			.managerName(registration.getManagerName())
			.repairList(registration.getRepairList())
			.carNumber(registration.getCarNumber())
			.carDistance(registration.getCarDistance())
			.estimatesImageUrl(registration.getEstimatesImageUrl())
			.carImageUrl(registration.getCarImageUrl())
			.date(registration.getDate())
			.build();
	}
}
