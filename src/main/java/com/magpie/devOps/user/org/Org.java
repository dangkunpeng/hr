package com.magpie.devOps.user.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Org {

	@Id
	private String orgId;
	@Column
	private String orgName;
	@Column
	private String status;
	@Column
	private String detail;
}
