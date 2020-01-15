package com.qushida.dao.impl;

import java.util.List;

import com.qushida.dao.NoticeDao;
import com.qushida.po.Notice;
import com.qushida.util.DBUtil;



public class NoticeDaoImpl implements NoticeDao {
	//创建工具类实例
	private DBUtil dbUtil = new DBUtil(); 
	@Override
	public List<Notice> getAllNotice() {
		List list = null;
		//写sql
		String sql = "select * from notice";
		//组织参数（无参数）
		try {
			list = dbUtil.getQueryList(Notice.class, sql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//添加新公告
	//1.实体类的每个属性分别入参
	@Override
	public int addNotice(String name,String content,String times) {
		int i = 0;
		//写sql
		//1.
		String sql = "insert into notice(name,content,times) values(?,?,?)";
		//2.String sql = "insert int notice(name,content,times) values(?,?,sysdate())";
		//组织参数
		//1.
		Object[] params ={name,content,times};
		//2.Object[] params ={name,content};
		//调用工具类方法执行sql
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	//根据id获取公告
	@Override
	public Notice selectById(int id) {
		Notice notice = null;
		//写sql
		String sql = "select * from notice where id=? ";
		//组织参数
		Object[] params = {id};
		//调用工具类执行sql语句
		try {
			notice = (Notice)dbUtil.getObject(Notice.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
    //删除公告
	@Override
	public int deleteNotice(int id) {
		int i = 0;
		//写sql
		String sql = "delete from notice where id= ?";
		//组织参数
		Object[] params = {id};
		//调用工具类执行sql
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
     //2.mysql sysdate()函数
	@Override
	public int addNotice(String name, String content) {
		int i = 0;
		//写sql
		String sql = "insert int notice(name,content,times) values(?,?,sysdate())";
		//组织参数
		Object[] params ={name,content};
		//调用工具类方法执行sql
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	//封装成对象
	@Override
	public int addNotice(Notice notice) {
		int i = 0;
		//写sql
		String sql = "insert into notice(name,content,times) values(?,?,sysdate())";	
		//组织参数
		Object[] params ={notice.getName(),notice.getContent()};
		//调用工具类方法执行sql
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
