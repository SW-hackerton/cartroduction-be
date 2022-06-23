package com.swhackathon.cartroduction.domain.user.dto;

import com.swhackathon.cartroduction.domain.user.domain.entity.User;
import com.swhackathon.cartroduction.domain.user.domain.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

	private String name;

	private String address;

	private Type type;

	public User toEntity() {
		return User.builder()
			.name(this.name)
			.address(this.address)
			.type(this.type)
			.build();
	}
}
