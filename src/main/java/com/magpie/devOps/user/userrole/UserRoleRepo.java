package com.magpie.devOps.user.userrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, String>{

	Long countByUserIdAndRoleId(String userId, String roleId);
}
