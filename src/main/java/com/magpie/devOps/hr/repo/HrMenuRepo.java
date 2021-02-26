package com.magpie.devOps.hr.repo;

import com.magpie.devOps.hr.entity.HrMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrMenuRepo extends JpaRepository<HrMenu, String> {
}
