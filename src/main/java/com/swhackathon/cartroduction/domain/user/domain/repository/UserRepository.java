package com.swhackathon.cartroduction.domain.user.domain.repository;

import com.swhackathon.cartroduction.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
