package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.time.LocalDateTime;
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

	private LocalDateTime date;

	public Registration toEntity() {
		return Registration.builder()
			.managerName(this.managerName)
			.category(this.category)
			.content(this.content)
			.price(this.price)
			.carNumber(this.carNumber)
			.carDistance(this.carDistance)
			.estimatesImageUrl(this.estimatesImageUrl)
			.carImageUrl(this.carImageUrl)
			.date(this.date)
			.build();
	}
}
