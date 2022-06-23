package com.swhackathon.cartroduction.domain.registration.dto;

import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepairListResponse {

	private Category category;

	private String content;

	private String price;

	public static List<RepairList> of(Category category, String content, String price) {
		final List<RepairList> repairLists = new ArrayList<>();
		repairLists.add(RepairList.builder()
			.category(category)
			.content(content)
			.price(price)
			.build());
		return repairLists;
	}
}
