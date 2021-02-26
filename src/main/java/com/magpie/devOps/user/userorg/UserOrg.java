package com.magpie.devOps.user.userorg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class UserOrg {
	@Id
	private String userOrgId;
	@Column
	private String userId;
	@Column
	private String orgId;
}
