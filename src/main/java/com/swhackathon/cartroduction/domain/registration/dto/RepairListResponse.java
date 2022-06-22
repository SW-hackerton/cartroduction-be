package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RepairListResponse {

	private Category category;

	private String content;

	private String price;

	public static RepairList of(Category category, String content, String price) {
		return RepairList.builder()
			.category(category)
			.content(content)
			.price(price)
			.build();
	}
}
