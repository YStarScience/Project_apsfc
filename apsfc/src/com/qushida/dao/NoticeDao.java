package com.qushida.dao;

import java.util.List;

import com.qushida.po.Notice;

public interface NoticeDao {
	//获取所有公告
	public List<Notice> getAllNotice();
	
	//添加公告
	//1.实体类的每个属性分别入参
	public int addNotice(String name,String content,String times);
	//2.mysql  sysdate（）函数
	public int addNotice(String name,String content);
	//3.封装成对象入参
	public int addNotice(Notice notice);
	
	//根据id获取公告
	public Notice selectById(int id);

	
	
	//删除
	public int deleteNotice(int id);
}
