package com.swhackathon.cartroduction.domain.dashboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class DashboardList {

	private final EngineOil engineOil;
	private final Tire tire;
	private final AccidentRepair accidentRepair;
	private final Flooding flooding;
	private final NormalRepair normalRepair;
	private final Inspection inspection;
}
