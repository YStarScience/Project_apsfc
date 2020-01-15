package com.qushida.dao.impl;

import org.junit.Test;

import com.qushida.dao.AdminDao;
import com.qushida.po.Admin;

public class AdminDaoImplTest {
	
	
	//测试登录
	@Test
	public void testLogin(){
		
		AdminDao adminDao = new AdminDaoImpl();
		
		Admin admin = adminDao.login("sa","123");
		
		System.out.println("admin:"+admin);
	}
}
