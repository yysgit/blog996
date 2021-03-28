package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbAdminUser;
import com.yys.szcp.mapper.DbAdminUserMapper;
import com.yys.szcp.service.DbAdminUserService;
import com.yys.szcp.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AdminUserServiceImpl implements DbAdminUserService {
	@Autowired
	private DbAdminUserMapper adminUserMapper;

	/**
	 * 管理员登陆
	 */
	@Override
	public DbAdminUser login(String username, String password) {
		//对密码加密
		password= MD5.MD5Pwd(username,password);
		Map map=new HashMap();
		map.put("adminName",username);
		map.put("adminPassword",password);
		List<DbAdminUser> admin = adminUserMapper.findAdminUserByUsernamePassword(map);
		if (admin != null && admin.size() > 0) {
			return admin.get(0);
		}
		return null;
	}

	@Override
	public DbAdminUser findAdminUserById(Integer adminUserId) {
		return adminUserMapper.findAdminUserById(adminUserId);
	}

	@Override
	public Map findAdminUserINFOById(Integer adminUserId) {
		return adminUserMapper.findAdminUserINFOById(adminUserId);
	}

	@Override
	public int addAdminUser(DbAdminUser adminUser) {
		return adminUserMapper.addAdminUser(adminUser);
	}

	@Override
	public List<Map> findAdminUserListByOrganId(Map map) {
		return adminUserMapper.findAdminUserListByOrganId(map);
	}

	@Override
	public int findAdminUserListByOrganIdCount(Map map) {
		return adminUserMapper.findAdminUserListByOrganIdCount(map);
	}

	@Override
	public int updateAdminUser(DbAdminUser adminUser) {
		return adminUserMapper.updateAdminUser(adminUser);
	}

	@Override
	public int deleteAdminUser(DbAdminUser adminUser) {
		return adminUserMapper.deleteAdminUser(adminUser);
	}

	@Override
	public List<DbAdminUser> findAdminUserByAdminName(Integer adminUserId,String username) {
		return adminUserMapper.findAdminUserByAdminName(adminUserId,username);
	}

	@Override
	public int editPasswordAdminUserInit(DbAdminUser adminUser) {
		return adminUserMapper.editPasswordAdminUserInit(adminUser);
	}

}
