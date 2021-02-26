package com.magpie.devOps.hr.repo;

import com.magpie.devOps.hr.entity.HrProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrProjectRepo extends JpaRepository<HrProject, String> {


}
