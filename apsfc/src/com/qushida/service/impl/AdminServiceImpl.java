package com.qushida.service.impl;

import com.qushida.dao.AdminDao;
import com.qushida.dao.impl.AdminDaoImpl;
import com.qushida.po.Admin;
import com.qushida.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao = new AdminDaoImpl();
	@Override
	public Admin login(String name, String pwd) {	
		//创建AdminDao对象来调用login方法	
		
		Admin admin = adminDao.login(name, pwd);
		return admin;
	}

	@Override
	public int updateAdminPwd(String name, String pwd) {
		return adminDao.updateAdminPwd(name, pwd);
	}

}
