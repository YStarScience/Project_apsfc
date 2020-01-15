package com.qushida.dao.impl;

import java.util.List;

import com.qushida.dao.MenuDao;
import com.qushida.po.Menu;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.MenuInfo;

public class MenuDaoImpl implements MenuDao {
	//创建DBUtil对象
	private DBUtil dbUtil = new DBUtil();
	@Override
	public Page getMenuByPage(Page page) {
		//写sql
		String sql = "select m.id , m.name , t.name as typename , m.burden , m.brief , m.price , m.sums , m.price1 , m.sums1 , m.imgpath from menus m , types t where m.typeid = t.id";
		
		//调用工具类中的分页查询方法
		Page curPage = dbUtil.getQueryPage(MenuInfo.class, sql, null, page);
		return curPage;
	}

	//添加菜单
	@Override
	public int addMenu(Menu menu) {
		int i = 0;
		//写sql
		String sql = "insert into menus(name,typeid,burden,brief,price,price1,imgpath) values (?,?,?,?,?,?,?)";
		//组织参数
		Object[] params = {menu.getName(),menu.getTypeid(),menu.getBurden(),menu.getBrief(),menu.getPrice(),menu.getPrice1(),menu.getImgpath()};
		//调用工具类中的方法执行添加
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//根据id获取对象
	@Override
	public Menu selectById(int id) {
		Menu menu = null;
		//写sql语句
		String sql = "select * from menus where id=?";
		Object[] params = {id};
		try {
			menu =(Menu)dbUtil.getObject(Menu.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}
	//删除
	@Override
	public int deleteMenu(int id) {
		int i = 0;
		String sql = "delete from menus where id=?";
		Object[] params = {id};
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//修改
	@Override
	public int updateMenu(Menu menu) {
		int i = 0;
		//写sql
		String sql = "update menus set name = ? , typeid = ? ,burden = ? , price = ? , price1 = ? , brief = ?  where id = ?";
		//组合参数(数组中参数的顺序和问号出现的顺序一致）
		Object[] params = {menu.getName(),menu.getTypeid(),menu.getBurden(),menu.getPrice(),menu.getPrice1(),menu.getBrief(),menu.getId()};
		//调用工具类中的更新方法
		try {
			i = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return i;
	}

	@Override
	public List<Menu> selectBySums1() {
		List menu = null;
		String sql = "select * from menus order by sums1 desc";
		try {
			menu = dbUtil.getQueryList(Menu.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}

}
