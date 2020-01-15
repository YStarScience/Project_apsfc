package com.qushida.service.impl;

import java.util.List;

import com.qushida.dao.TypeDao;
import com.qushida.dao.impl.TypeDaoImpl;
import com.qushida.po.Type;
import com.qushida.service.TypeService;

public class TypeServiceImpl implements TypeService {
	//创建Dao对象
	TypeDao typeDao = new TypeDaoImpl(); 	  //多态
	
	@Override
	public List<Type> getAllType() {	
		List<Type> list = typeDao.getAllType();
		return list;
	}
	
	//添加类别
	@Override
	public int addType(String name) {
		
		//判断想要添加的类别是否存在(避免重复添加)
		return typeDao.addType(name);
		
	}
	
	//根绝id获取类别
	@Override
	public Type selectByID(int id) {
		return typeDao.selectByID(id);
	}
	
	//更新操作
	@Override
	public int update(Type type) {
		return typeDao.update(type);
	}

	//删除
	@Override
	public int deleteType(int id) {
		return typeDao.deleteType(id);
	}

}
