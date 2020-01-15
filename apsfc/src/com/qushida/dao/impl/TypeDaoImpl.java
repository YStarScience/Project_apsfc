package com.qushida.dao.impl;

import java.util.List;

import com.qushida.dao.TypeDao;
import com.qushida.po.Type;
import com.qushida.util.DBUtil;


public class TypeDaoImpl implements TypeDao {
	
	
	//3.创建DBUtil对象
	private DBUtil dbUtil= new DBUtil();
	//获取所有的类别
	@Override
	public List<Type> getAllType() {
		List list = null;
		//1.写sql语句
		String sql = "select * from types";
		//2.组织参数
		
		try {
			//调用获取List的方法
			list = dbUtil.getQueryList(Type.class,sql,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//添加类别
	@Override
	public int addType(String name) {
		int i = 0;
		//sql语句
		String sql = "insert into types(name) values (?)";
		//组织参数
		Object[]params = {name};
		//调用工具类中的添加方法
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	//根据id获取类别
	@Override
	public Type selectByID(int id) {
		Type type = null;
		//写sql语句
		String sql = "select * from types where id = ?";
		//组织参数
		Object[] params = {id};
		//调用工具类对应方法完成查询
		try {
			type = (Type)dbUtil.getObject(Type.class, sql, params);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return type;
	}

	//更新操作
	@Override
	public int update(Type type) {
		int i = 0;
		//写sql
		String sql = "update types set name = ? where id = ?";
		//组合参数(数组中参数的顺序和问号出现的顺序一致）
		Object[] params = {type.getName(),type.getId()};
		//调用工具类中的更新方法
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return i;
	}

	//删除
	@Override
	public int deleteType(int id) {
		int i = 0;
		//写sql
		String sql = "delete from types where id = ?";
		//组织参数列表
		Object[] params = {id};
		//调用工具类方法
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return i;
	}

}
