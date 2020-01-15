package com.qushida.dao;

import java.util.List;

import com.qushida.po.Menu;
import com.qushida.util.Page;
import com.qushida.vo.MenuInfo;

public interface MenuDao {
	//分页查询菜单
	public Page getMenuByPage(Page page);
	//添加新菜单
	public int addMenu(Menu menu);
	//根据id获取菜单
	public Menu selectById(int id);
	//删除
	public int deleteMenu(int id);
	//修改
	public int updateMenu(Menu menu);
	public List<Menu> selectBySums1();
}
