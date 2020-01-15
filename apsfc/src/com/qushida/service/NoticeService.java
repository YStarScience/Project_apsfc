package com.qushida.service;

import java.util.List;

import com.qushida.po.Notice;

public interface NoticeService {
	//获取所有公告
	public List<Notice> getAllNotice();
	//按照日期降序查询前三条公告
	
	//添加新公告
	public int addNotice(String name,String content,String times);
	public int addNotice(String name,String content);
	public int addNotice(Notice notice);
	
	//删除公告
	public int deleteNotice(int id);
	//根据id获取公告
	public Notice selectById(int id);
}
