package com.magpie.devOps.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magpie.devOps.user.org.Org;
import com.magpie.devOps.user.role.Role;
import com.magpie.devOps.user.user.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/user/manage", method = { RequestMethod.POST, RequestMethod.GET })
public class UserController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(value = "/query/user")
    public String queryUser() {
    	log.info("查询用户列表");
    	return this.userService.userQuery();
    }
    
    @RequestMapping(value = "/update/user")
    public String updateUser(@RequestBody User user) {
    	log.info("保存用户信息");
    	return this.userService.userAdd(user).toString();
    }
    
    @RequestMapping(value = "/query/org")
    public String queryOrg() {
    	log.info("查询组织列表");
    	return this.userService.orgQuery();
    }
    
    @RequestMapping(value = "/update/org")
    public String updateUser(@RequestBody Org org) {
    	log.info("保存组织信息");
    	return this.userService.orgAdd(org).toString();
    }
    
    @RequestMapping(value = "/query/role")
    public String queryRole() {
    	log.info("查询角色列表");
    	return this.userService.roleQuery();
    }
    
    @RequestMapping(value = "/update/role")
    public String updateRole(@RequestBody Role role) {
    	log.info("保存角色信息");
    	return this.userService.roleAdd(role).toString();
    }
}
