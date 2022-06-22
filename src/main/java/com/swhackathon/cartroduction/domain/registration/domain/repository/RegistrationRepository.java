package com.swhackathon.cartroduction.domain.registration.domain.repository;

import com.swhackathon.cartroduction.domain.registration.domain.entity.Registration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	List<Registration> findByCarNumber(String carNumber);
}
