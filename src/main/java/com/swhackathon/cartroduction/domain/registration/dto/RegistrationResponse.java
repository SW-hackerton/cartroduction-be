package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {

	private String managerName;

	private Category category;

	private String content;

	private String price;

	private String carNumber;

	private String carDistance;

	private String estimatesImageUrl;

	private String carImageUrl;

	private LocalDateTime date;

	public static Registration of(Registration registration) {
		return Registration.builder()
			.id(registration.getId())
			.managerName(registration.getManagerName())
			.category(registration.getCategory())
			.content(registration.getContent())
			.price(registration.getPrice())
			.carNumber(registration.getCarNumber())
			.carDistance(registration.getCarDistance())
			.estimatesImageUrl(registration.getEstimatesImageUrl())
			.carImageUrl(registration.getCarImageUrl())
			.date(registration.getDate())
			.build();
	}
}
