package com.magpie.devOps.hr.repo;

import com.magpie.devOps.hr.entity.HrPeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrPeopleRepo extends JpaRepository<HrPeople, String> {


}
