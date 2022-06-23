package com.swhackathon.cartroduction.domain.user.domain.entity;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import com.swhackathon.cartroduction.domain.user.domain.enumeration.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "registration_form_id")
	private Registration registration;

	private String name;

	private String address;

	private String password;

	private Type type;
}
