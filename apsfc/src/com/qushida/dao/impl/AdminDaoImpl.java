package com.qushida.dao.impl;

import com.qushida.dao.AdminDao;
import com.qushida.po.Admin;
import com.qushida.util.DBUtil;

public class AdminDaoImpl implements AdminDao {
	//3.创建工具类DBUtil对象
	private DBUtil dbUtil = new DBUtil();
	//登录
	@Override
	public Admin login(String name, String pwd) {
		Admin admin = null;
		//1.写sql语句
		String sql="select *from admin where name=? and pwd =?";
		//2.组织参数
		Object[] params = {name,pwd};

		//4.调用工具类的方法
		try {
			admin = (Admin)dbUtil.getObject(Admin.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
		
	}

	//修改管理员密码
	@Override
	public int updateAdminPwd(String name ,String pwd) {
		int i = 0;
		String sql = "update admin set pwd= ? where name = ?";
		Object[] params = {pwd,name};
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
