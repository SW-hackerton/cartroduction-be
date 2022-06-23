package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepairListRequest {

	private Category category;

	private String content;

	private int price;

	public RepairList toEntity() {
		return RepairList.builder()
			.category(this.category)
			.content(this.content)
			.price(this.price).build();
	}
}
