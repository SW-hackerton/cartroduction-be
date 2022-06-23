package com.swhackathon.cartroduction.domain.dashboard.domain;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Dashboard {

	private final List<RepairInfo> repairInfo;
	private final DashboardList dashboardList;
}
