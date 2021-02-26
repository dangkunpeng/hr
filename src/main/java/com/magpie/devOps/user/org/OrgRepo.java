package com.magpie.devOps.user.org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrgRepo extends JpaRepository<Org, String>{

	public Long countByOrgName(String orgName);
}
