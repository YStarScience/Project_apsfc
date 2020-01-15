package com.qushida.service.impl;

import java.util.List;

import com.qushida.dao.NoticeDao;
import com.qushida.dao.impl.NoticeDaoImpl;
import com.qushida.po.Notice;
import com.qushida.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
    //创建Dao对象
	NoticeDao noticeDao = new NoticeDaoImpl();
	//获取所有公告
	@Override
	public List<Notice> getAllNotice() {	
		return noticeDao.getAllNotice();
	}
	//添加新公告
	@Override
	public int addNotice(String name, String content, String times) {
		return noticeDao.addNotice(name, content, times);
	}
/*	public int addNotice(String name, String content) {
		return noticeDao.addNotice(name, content);
	}*/
	//删除公告
	@Override
	public int deleteNotice(int id) {
		return noticeDao.deleteNotice(id);
	}
	@Override
	public int addNotice(String name, String content) {
		// TODO Auto-generated method stub
		return noticeDao.addNotice(name, content);
	}
	@Override
	public int addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.addNotice(notice);
	}
	@Override
	public Notice selectById(int id) {
		// TODO Auto-generated method stub
		return noticeDao.selectById(id);
	}

}
