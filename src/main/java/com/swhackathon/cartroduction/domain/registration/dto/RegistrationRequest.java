package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationRequest {

	private String managerName;

	private Category category;

	private String content;

	private String price;

	private String carNumber;

	private String carDistance;

	private String estimatesImageUrl;

	private String carImageUrl;

	private LocalDate date;

	public Registration toEntity() {

		final List<RepairList> repairList = RepairListResponse.of(category, content, price);
		return Registration.builder()
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
