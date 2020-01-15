package com.qushida.dao;

import com.qushida.po.Admin;

public interface AdminDao {
	//登录
	//写方法：1.返回值 2.参数列表
	public Admin login(String name ,String pwd);
	//修改密码
	int  updateAdminPwd(String name ,String pwd);
}
