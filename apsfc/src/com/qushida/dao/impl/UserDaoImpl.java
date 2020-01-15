package com.qushida.dao.impl;

import com.qushida.dao.UserDao;
import com.qushida.po.User;
import com.qushida.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	//3.创建工具类DBUtil对象
	private DBUtil dbUtil = new DBUtil();
	//用户登录
	@Override
	public User login(String name, String pwd) {
		User user = null;
		//1.写sql语句
		String sql="select *from users where name=? and pwd =?";
		//2.组织参数
		Object[] params = {name,pwd};
		/*//3.创建工具类DBUtil对象
		DBUtil dbUtil = new DBUtil();*/
		//4.调用工具类的方法
		try {
			user = (User)dbUtil.getObject(User.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}

	//注册
	@Override
	public int regist(User user) {
		int i = 0;
		String sql = "insert into users(name,pwd,realname,sex,age,card,address,phone,email,code) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getName(),user.getPwd(),user.getRealname(),user.getSex(),user.getAge(),user.getCard(),user.getAddress(),user.getPhone(),user.getEmail(),user.getCode()};
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int modify(User user) {
		int i = 0;
		String sql = "update users set pwd=?,realname=?,age=?,card=?,address=?,phone=?,email=?,code=? where name=?";
		Object[] params = {user.getPwd(),user.getRealname(),user.getAge(),user.getCard(),user.getAddress(),user.getPhone(),user.getEmail(),user.getCode(),user.getName()};
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
