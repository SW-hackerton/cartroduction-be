package com.swhackathon.cartroduction.domain.registration.domain.entity;

import com.swhackathon.cartroduction.domain.user.domain.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {

	@Id
	@Column(name = "registration_form_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "manager_name", nullable = false)
	private String managerName;

	@Column(name = "repair_list", nullable = false)
	private RepairList repairList;

	@Column(name = "car_number", nullable = false)
	private String carNumber;

	@Column(name = "car_distance", nullable = false)
	private String carDistance;

	@Column(name = "estimates_image_url", nullable = false)
	private String estimatesImageUrl;

	@Column(name = "car_image_url", nullable = false)
	private String carImageUrl;

	@CreationTimestamp
	private LocalDateTime date;
}
