package com.swhackathon.cartroduction.domain.registration.domain.entity;

import com.swhackathon.cartroduction.domain.registration.domain.enumeration.Category;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private User user;

	@Column(name = "manager_name", nullable = false)
	private String managerName;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String content;

	private String price;

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
