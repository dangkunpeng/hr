package com.magpie.devOps.user.userorg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrgRepo extends JpaRepository<UserOrg, String>{

	Long countByUserIdAndOrgId(String userId, String orgId);
}
