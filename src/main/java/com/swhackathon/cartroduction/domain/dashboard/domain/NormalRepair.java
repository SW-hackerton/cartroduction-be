package com.swhackathon.cartroduction.domain.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NormalRepair {

	private final int count;
	private final int totalRepairPrice;
}
