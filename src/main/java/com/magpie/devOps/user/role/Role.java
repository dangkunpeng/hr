package com.magpie.devOps.user.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Role {
	@Id
	private String roleId;
	@Column
	private String roleName;
	@Column
	private String status;
	@Column
	private String detail;
}
