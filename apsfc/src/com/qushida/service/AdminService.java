package com.qushida.service;

import com.qushida.po.Admin;

public interface AdminService {
	//管理员登陆
	public Admin login(String name ,String pwd);
	//修改管理员密码
	public int updateAdminPwd(String name ,String pwd);
}
