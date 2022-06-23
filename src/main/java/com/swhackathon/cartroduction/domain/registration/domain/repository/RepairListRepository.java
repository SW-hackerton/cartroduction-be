package com.swhackathon.cartroduction.domain.registration.domain.repository;

import com.swhackathon.cartroduction.domain.registration.domain.entity.RepairList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairListRepository extends JpaRepository<RepairList, Long> {

}
