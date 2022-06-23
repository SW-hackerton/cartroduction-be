package com.swhackathon.cartroduction.domain.registration.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
	엔진오일교체(0, "엔진오일교체"),
	타이어교체(1, "타이어교체"),
	기타오일교체(2, "기타오일교체"),
	사고수리(3, "사고수리"),
	침수수리(4, "침수수리"),
	일반수리(5, "일반수리"),
	차량점검(6, "차량점검"),
	기타(7, "기타");

	private final int index;
	private final String name;
}
