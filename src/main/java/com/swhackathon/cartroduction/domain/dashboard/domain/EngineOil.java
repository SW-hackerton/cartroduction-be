package com.swhackathon.cartroduction.domain.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EngineOil {

	public final int replacementCycle;
	public final int count;
}
