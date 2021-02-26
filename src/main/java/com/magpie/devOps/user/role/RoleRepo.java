package com.magpie.devOps.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepo extends JpaRepository<Role, String>{

	Long countByRoleName(String roleName);
}
