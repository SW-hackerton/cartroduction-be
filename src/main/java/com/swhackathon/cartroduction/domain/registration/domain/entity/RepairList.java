package com.swhackathon.cartroduction.domain.registration.domain.entity;

import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RepairList {

	@Id
	@Column(name = "repair_list_id", nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String content;

	private String price;
}
