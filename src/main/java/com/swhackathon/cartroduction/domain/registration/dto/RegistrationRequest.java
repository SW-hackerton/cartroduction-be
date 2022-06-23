package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationRequest {

	private long id;
	private String managerName;

	private List<RepairListRequest> repairList;

	private String carNumber;

	private String carDistance;

	private String estimatesImageUrl;

	private String carImageUrl;

	private LocalDate date;

	public Registration toEntity() {
		final List<RepairList> repairList = this.repairList.stream()
			.map(RepairListRequest::toEntity)
			.collect(Collectors.toList());
		return Registration.builder()
			.id(this.id)
			.managerName(this.managerName)
			.repairList(repairList)
			.carNumber(this.carNumber)
			.carDistance(this.carDistance)
			.estimatesImageUrl(this.estimatesImageUrl)
			.carImageUrl(this.carImageUrl)
			.date(this.date)
			.build();
	}
}
