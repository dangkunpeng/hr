package com.magpie.devOps.user;

import com.magpie.devOps.user.org.Org;
import com.magpie.devOps.user.role.Role;
import com.magpie.devOps.user.user.User;
import com.magpie.devOps.user.userorg.UserOrg;
import com.magpie.devOps.user.userrole.UserRole;
import com.magpie.devOps.utils.result.Result;

public interface UserService {

	/**
	 * 用户查询
	 * @return
	 */
	public String userQuery();
	/**
	 * 组织查询
	 * @return
	 */
	public String orgQuery();
	/**
	 * 角色查询
	 * @return
	 */
	public String roleQuery();
	/**
	 * 用户和组织查询
	 * @return
	 */
	public String userOrgQuery();
	/**
	 * 用户和角色查询
	 * @return
	 */
	public String userRoleQuery();
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public Result userAdd(User user);
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public Result userDel(String userId);
	/**
	 * 增加组织
	 * @param org
	 * @return
	 */
	public Result orgAdd(Org org);
	/**
	 * 删除组织
	 * @param orgId
	 * @return
	 */
	public Result orgDel(String orgId);
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public Result roleAdd(Role role);
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	public Result roleDel(String roleId);
	/**
	 * 增加关系：用户和组织
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public Result userOrgAdd(UserOrg userOrg);
	/**
	 * 删除关系：用户和组织
	 * @param userOrgId
	 * @return
	 */
	public Result userOrgDel(String userOrgId);
	/**
	 * 增加关系：用户和角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public Result userRoleAdd(UserRole userRole);
	/**
	 * 删除关系：用户和角色
	 * @param userRoleId
	 * @return
	 */
	public Result userRoleDel(String userRoleId);
}
