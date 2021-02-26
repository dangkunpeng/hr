package com.magpie.devOps.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.magpie.devOps.user.org.Org;
import com.magpie.devOps.user.org.OrgRepo;
import com.magpie.devOps.user.role.Role;
import com.magpie.devOps.user.role.RoleRepo;
import com.magpie.devOps.user.user.User;
import com.magpie.devOps.user.user.UserRepo;
import com.magpie.devOps.user.userorg.UserOrg;
import com.magpie.devOps.user.userorg.UserOrgRepo;
import com.magpie.devOps.user.userrole.UserRole;
import com.magpie.devOps.user.userrole.UserRoleRepo;
import com.magpie.devOps.utils.Utils;
import com.magpie.devOps.utils.result.RestResult;
import com.magpie.devOps.utils.result.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private OrgRepo orgRepo;

	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	private UserOrgRepo userOrgRepo;

	@Override
	public Result userAdd(User user) {
		// 空判断
		if (StringUtils.isAnyBlank(user.getUserName(), user.getPassword())) {
			return RestResult.fail("用户名和密码不能为空");
		}
		if (StringUtils.isBlank(user.getUserId())) {
			// 新增：重复判断
			Long count = this.userRepo.countByUserName(user.getUserName());
			if (count > 0) {
				return RestResult.fail("名称已占用");
			}
			// 设置主键
			user.setUserId(Utils.getKey());
			// 新增
			this.userRepo.save(user);
			log.info("新增记录");
		} else {
			// 修改
			this.userRepo.save(user);
			log.info("修改记录");
		}
		return RestResult.success(user);
	}

	@Override
	public Result userDel(String userId) {
		// 空判断
		if (StringUtils.isBlank(userId)) {
			return RestResult.fail("参数有误");
		}
		// 删除
		this.userRepo.deleteById(userId);
		return RestResult.success(userId);
	}

	@Override
	public Result orgAdd(Org org) {
		// 空判断
		if (StringUtils.isBlank(org.getOrgName())) {
			return RestResult.fail("参数有误");
		}
		if (StringUtils.isBlank(org.getOrgId())) {
			// 新增：重复判断
			Long count = this.orgRepo.countByOrgName(org.getOrgName());
			if (count > 0) {
				return RestResult.fail("名称已占用");
			}
			// 设置主键
			org.setOrgId(Utils.getKey());
			// 新增
			this.orgRepo.save(org);
			log.info("新增记录");
		} else {
			// 修改
			this.orgRepo.save(org);
			log.info("修改记录");
		}
		return RestResult.success(org);
	}

	@Override
	public Result orgDel(String orgId) {
		// 空判断
		if (StringUtils.isBlank(orgId)) {
			return RestResult.fail("参数有误");
		}
		// 删除
		this.orgRepo.deleteById(orgId);
		return RestResult.success(orgId);
	}

	@Override
	public Result roleAdd(Role role) {
		// 空判断
		if (StringUtils.isBlank(role.getRoleName())) {
			return RestResult.fail("参数有误");
		}
		if (StringUtils.isBlank(role.getRoleId())) {
			// 新增：重复判断
			Long count = this.roleRepo.countByRoleName(role.getRoleName());
			if (count > 0) {
				return RestResult.fail("名称已占用");
			}
			// 设置主键
			role.setRoleId(Utils.getKey());
			// 新增
			this.roleRepo.save(role);
			log.info("新增记录");
		} else {
			// 修改
			this.roleRepo.save(role);
			log.info("修改记录");
		}
		return RestResult.success(role);
	}

	@Override
	public Result roleDel(String roleId) {
		// 空判断
		if (StringUtils.isBlank(roleId)) {
			return RestResult.fail("参数有误");
		}
		// 删除
		this.roleRepo.deleteById(roleId);
		return RestResult.success(roleId);
	}

	@Override
	public Result userOrgAdd(UserOrg userOrg) {
		// 空判断
		if (StringUtils.isAnyBlank(userOrg.getUserId(), userOrg.getOrgId())) {
			return RestResult.fail("参数有误");
		}
		if (StringUtils.isBlank(userOrg.getUserOrgId())) {
			// 新增：重复判断
			Long count = this.userOrgRepo.countByUserIdAndOrgId(userOrg.getUserId(), userOrg.getOrgId());
			if (count > 0) {
				return RestResult.fail("名称已占用");
			}
			// 设置主键
			userOrg.setUserOrgId(Utils.getKey());
			// 新增
			this.userOrgRepo.save(userOrg);
			log.info("新增记录");
		} else {
			// 修改
			this.userOrgRepo.save(userOrg);
			log.info("修改记录");
		}
		return RestResult.success(userOrg);
	}

	@Override
	public Result userOrgDel(String userOrgId) {
		// 空判断
		if (StringUtils.isBlank(userOrgId)) {
			return RestResult.fail("参数有误");
		}
		// 删除
		this.userOrgRepo.deleteById(userOrgId);
		return RestResult.success(userOrgId);
	}

	@Override
	public Result userRoleAdd(UserRole userRole) {
		// 空判断
		if (StringUtils.isAnyBlank(userRole.getUserId(), userRole.getRoleId())) {
			return RestResult.fail("参数有误");
		}
		if (StringUtils.isBlank(userRole.getUserRoleId())) {
			// 新增：重复判断
			Long count = this.userRoleRepo.countByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
			if (count > 0) {
				return RestResult.fail("名称已占用");
			}
			// 设置主键
			userRole.setUserRoleId(Utils.getKey());
			// 新增
			this.userRoleRepo.save(userRole);
			log.info("新增记录");
		} else {
			// 修改
			this.userRoleRepo.save(userRole);
			log.info("修改记录");
		}
		return RestResult.success(userRole);
	}

	@Override
	public Result userRoleDel(String userRoleId) {
		// 空判断
		if (StringUtils.isBlank(userRoleId)) {
			return RestResult.fail("参数有误");
		}
		// 删除
		this.userRoleRepo.deleteById(userRoleId);
		return RestResult.success(userRoleId);
	}

	@Override
	public String userQuery() {
		return JSONObject.toJSONString(this.userRepo.findAll());
	}

	@Override
	public String orgQuery() {
		return JSONObject.toJSONString(this.orgRepo.findAll());
	}

	@Override
	public String roleQuery() {
		return JSONObject.toJSONString(this.roleRepo.findAll());
	}

	@Override
	public String userOrgQuery() {
		return JSONObject.toJSONString(this.userOrgRepo.findAll());
	}

	@Override
	public String userRoleQuery() {
		return JSONObject.toJSONString(this.userRoleRepo.findAll());
	}
}
