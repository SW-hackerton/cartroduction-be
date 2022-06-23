package com.swhackathon.cartroduction.domain.dashboard.dto;

import com.swhackathon.cartroduction.domain.dashboard.domain.AccidentRepair;
import com.swhackathon.cartroduction.domain.dashboard.domain.DashboardList;
import com.swhackathon.cartroduction.domain.dashboard.domain.EngineOil;
import com.swhackathon.cartroduction.domain.dashboard.domain.Flooding;
import com.swhackathon.cartroduction.domain.dashboard.domain.Inspection;
import com.swhackathon.cartroduction.domain.dashboard.domain.NormalRepair;
import com.swhackathon.cartroduction.domain.dashboard.domain.RepairInfo;
import com.swhackathon.cartroduction.domain.dashboard.domain.Tire;
import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponse {

	private List<RepairInfo> repairInfo;

	private DashboardList dashboardList;

	public static DashboardResponse of(List<RepairInfo> repairInfo, List<Registration> registrations) {
		return DashboardResponse.builder()
			.repairInfo(repairInfo)
			.dashboardList(calculateDashboardList(registrations))
			.build();
	}


	private static DashboardList calculateDashboardList(List<Registration> registrations) {
		//engineoil
		List<Integer> engineReplacementKmList = new ArrayList<>();

		//tire
		List<Integer> tireReplacementKmList = new ArrayList<>();

		//accident
		int accidentCount = 0;
		int accidentTotalRepairPrice = 0;

		//flooding
		Boolean isFlooding = false;

		//normal repair
		int normalCount = 0;
		int normalRepairPrice = 0;

		//inspection
		List<Integer> inspectionReplacementKmList = new ArrayList<>();

		for (Registration registration : registrations) {
			List<RepairList> repairLists = registration.getRepairList();
			for (RepairList repairList : repairLists) {
				if (repairList.getCategory().equals(Category.엔진오일교체)) {
					engineReplacementKmList.add(registration.getCarDistance());
				}
				if (repairList.getCategory().equals(Category.타이어교체)) {
					tireReplacementKmList.add(registration.getCarDistance());
				}
				if (repairList.getCategory().equals(Category.사고수리)) {
					accidentCount++;
					accidentTotalRepairPrice += repairList.getPrice();
				}
				if (repairList.getCategory().equals(Category.침수수리)) {
					isFlooding = true;
				}
				if (repairList.getCategory().equals(Category.일반수리)) {
					normalCount++;
					normalRepairPrice += repairList.getPrice();
				}
				if (repairList.getCategory().equals(Category.차량점검)) {
					inspectionReplacementKmList.add(registration.getCarDistance());
				}

			}
		}

		int engineReplacementCycle = calculateDifferenceKm(engineReplacementKmList);
		int tireReplacementCycle = calculateDifferenceKm(tireReplacementKmList);
		int inspectionReplacementCycle = calculateDifferenceKm(inspectionReplacementKmList);

		EngineOil engineOil = new EngineOil(engineReplacementCycle, engineReplacementKmList.size());
		Tire tire = new Tire(tireReplacementCycle, tireReplacementKmList.size());
		AccidentRepair accidentRepair = new AccidentRepair(accidentCount, accidentTotalRepairPrice);
		Flooding flooding = new Flooding(isFlooding);
		NormalRepair normalRepair = new NormalRepair(normalCount, normalRepairPrice);
		Inspection inspection = new Inspection(inspectionReplacementCycle, inspectionReplacementKmList.size());
		return new DashboardList(engineOil, tire, accidentRepair, flooding, normalRepair,
			inspection);
	}

	private static Integer calculateDifferenceKm(List<Integer> replacementKmList) {
		if (replacementKmList.isEmpty()) {
			return 0;
		}
		int size = replacementKmList.size();
		int firstKm = replacementKmList.get(0);
		if (size == 1) {
			return firstKm;
		}
		List<Integer> differences = new ArrayList<>();
		for (int i = 0; i < size - 1; i++) {
			differences.add(replacementKmList.get(i + 1) - replacementKmList.get(i));
		}
		return (differences.stream().mapToInt(Integer::intValue).sum() + firstKm) / (size);
	}
}
