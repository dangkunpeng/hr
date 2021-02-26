package com.magpie.devOps.user.userrole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class UserRole {
	@Id
	private String userRoleId;
	@Column
	private String userId;
	@Column
	private String roleId;
}
