package com.magpie.devOps.user.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<User, String>{

	Long countByUserName(String userName);
}
