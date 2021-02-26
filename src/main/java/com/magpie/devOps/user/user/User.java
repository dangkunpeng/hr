package com.magpie.devOps.user.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User {

	@Id
	private String userId;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String status;
	
	@Column
	private String detail;
}
