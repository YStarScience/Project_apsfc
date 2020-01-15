package com.qushida.service.impl;

import java.util.List;

import com.qushida.dao.MenuDao;
import com.qushida.dao.impl.MenuDaoImpl;
import com.qushida.po.Menu;
import com.qushida.service.MenuService;
import com.qushida.util.Page;

public class MenuServiceImpl implements MenuService {
	//创建Dao层对象
	private MenuDao menuDao = new MenuDaoImpl();
	//分页显示菜单
	@Override
	public Page getMenuByPage(Page page) {
		Page curpage = menuDao.getMenuByPage(page);
		return curpage;
	}	
	//添加菜单
	@Override
	public int addMenu(Menu menu) {		
		return menuDao.addMenu(menu);
	}
	//根据id获取菜单
	@Override
	public Menu selectById(int id) {	
		return menuDao.selectById(id);
	}
	//删除
	@Override
	public int deleteMenu(int id) {
		return menuDao.deleteMenu(id);
	}
	@Override
	public int updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.updateMenu(menu);
	}
	@Override
	public List<Menu> selectBySums1() {
		return menuDao.selectBySums1();
	}

}
