package com.qushida.service;

import com.qushida.po.User;

public interface UserService {
	//用户登陆
	public User login(String name ,String pwd);
	//用户注册
	public int regist(User user);
	public int modify(User user);
}
