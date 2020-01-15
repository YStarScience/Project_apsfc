package com.qushida.service.impl;


import com.qushida.dao.UserDao;
import com.qushida.dao.impl.UserDaoImpl;
import com.qushida.po.User;
import com.qushida.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public User login(String name, String pwd) {
		/*//创建AdminDao对象来调用login方法	
		UserDao userDao = new UserDaoImpl();*/
		User user = userDao.login(name, pwd);
		return user;
	}

	@Override
	public int regist(User user) {
		
		return userDao.regist(user);
	}

	@Override
	public int modify(User user) {
		return userDao.modify(user);
	}

}
