package com.qushida.dao;

import com.qushida.po.User;

//users表对应的实体类
public interface UserDao {
	//用户登录
	public User login(String name ,String pwd);
	//用户注册
	public int regist(User user);
	public int modify(User user);
}
