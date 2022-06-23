package com.swhackathon.cartroduction.domain.registration.domain.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {

	@Id
	@Column(name = "registration_form_id", nullable = false)
	private Long id;

	@Column(name = "manager_name", nullable = false)
	private String managerName;

	@OneToMany(cascade = CascadeType.ALL)
	private List<RepairList> repairList;

	@Column(name = "car_number", nullable = false)
	private String carNumber;

	@Column(name = "car_distance", nullable = false)
	private String carDistance;

	@Column(name = "estimates_image_url", nullable = false)
	private String estimatesImageUrl;

	@Column(name = "car_image_url", nullable = false)
	private String carImageUrl;

	@CreationTimestamp
	private LocalDate date;

	@Override
	public String toString() {
		return "Registration{" +
				"id=" + id +
				", managerName='" + managerName + '\'' +
				", repairList=" + repairList +
				", carNumber='" + carNumber + '\'' +
				", carDistance='" + carDistance + '\'' +
				", estimatesImageUrl='" + estimatesImageUrl + '\'' +
				", carImageUrl='" + carImageUrl + '\'' +
				", date=" + date +
				'}';
	}
}
