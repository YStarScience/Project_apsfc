package com.qushida.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.qushida.dao.OrderDao;
import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;
import com.qushida.vo.OrderStatistic;

public class OrderDaoImpl implements OrderDao {
	private DBUtil dbUtil = new DBUtil();

	@Override
	public int addOrder(Order order) throws Exception {
		String sql = "insert into orders (userid,menuid,menusum,times,delivery) values(?,?,?,sysdate(),'0')";

		Object[] params = { order.getUserid(), order.getMenuid(), order.getMenusum() };

		int rows = dbUtil.execute(DBUtil.getConnection(), sql, params);

		return rows;
	}

//	// 查询用户的所有订单
//	@Override
//	public List<OrderInfo> getAllOrderByUserId(int userId) {
//		List orderList = null;
//		String sql = "select m.name,u.realname,u.phone,u.address,o.menusum,m.price1,o.times,o.delivery  from users u,orders o,menus m   where u.id=o.userid and m.id=o.menuid and o.userid=?";
//		Object[] params = { userId };
//		try {
//			orderList = dbUtil.getQueryList(OrderInfo.class, sql, params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return orderList;
//	}

	// 分页查询所有订单

	@Override
	public Page queryOrderByPage(Page page,OrderCriteria criteria) {
		/*
		 * select o.id orderId,u.id userId ,u.realname realName
		 * ,u.phone,u.address, m.name menuName,o.menusum menuSum
		 * ,m.price1,o.times,o.delivery from orders o INNER JOIN users u ON u.id
		 * = o.userid INNER JOIN menus m ON m.id = o.menuid ORDER BY o.times
		 * DESC
		 */
		// 构建sql
		StringBuffer sql = new StringBuffer();
		sql.append(
				" select o.id orderId,u.id userId ,u.realname realName ,u.phone,u.address, m.name menuName,o.menusum menuSum ,m.price1,o.times,o.delivery ")
				.append(" from orders o ").append(" INNER JOIN users u ON u.id = o.userid ")
				.append(" INNER JOIN menus m ON m.id = o.menuid ")
				.append(" where 1=1");
		List<Object> params = new ArrayList<Object>();
		
		if (criteria!=null) {
			//有查询条件
			//获取四个条件
			int userId = criteria.getUserId();
			if (userId>0) {
				//拼接sql
				sql.append(" and u.id=?");
				params.add(userId);
			}
			String menuName = criteria.getMenuName();
			if (menuName!=null&&!"".equals(menuName.trim())) {
				sql.append(" and m.name like ?");
				params.add("%"+menuName+"%");
			}
			String date = criteria.getDate();
			if (date!=null&&!"".equals(date.trim())) {
				sql.append(" and o.times like ?");
				params.add("%"+date+"%");
			}
			int delivery = criteria.getDelivery();
			if (delivery!=-1) {
				sql.append(" and o.delivery = ?");
				params.add(delivery);
			}
			//根据查询条件的内容动态生成sql
		}
		sql.append(" ORDER BY o.times DESC");
		try {
			page = dbUtil.getQueryPage(OrderInfo.class, sql.toString(), params.toArray(), page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	// 删除
	@Override
	public int deleteOrderById(int orderId) {
		int rows = 0;
		String sql = "delete from orders where id= ?";
		Object[] params = { orderId };
		try {
			rows = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	// 更新修改
	@Override
	public int updateOrderById(int orderId) {
		int rows = 0;
		String sql = "update orders set delivery = '1' where id= ?";
		Object[] params = { orderId };
		try {
			rows = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public List<OrderStatistic> queryOrderByDelivery() {
		List list = null;
		String sql = "select m.name as name,sum(o.menusum) as sums1,m.price1 as price1,sum(o.menusum)*m.price1 as total from orders o,menus m where o.menuid = m.id group by name";
		 try {
			list =  dbUtil.getQueryList(OrderStatistic.class, sql,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in orderdaoimpl-------------------------------------");
		return list;
	}

	@Override
	public Page queryUserOrderByPage(Page page, OrderCriteria Criteria, int id,int de) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select u.id userId ,m.name menuName,u.realname realName,u.phone,u.address,o.menusum menuSum ,m.price1,o.times,o.delivery")
				.append(" from orders o,menus m ,users u ")
				.append(" where u.id=o.userid and o.menuid= m.id and u.id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		if (de==1||de==0) {
			sql.append(" and o.delivery=?");
			params.add(de);
		}
		if (Criteria!=null) {
			String menuName = Criteria.getMenuName();
			if (menuName!=null&&!"".equals(menuName.trim())) {
				sql.append(" and m.name like ?");
				params.add("%"+menuName+"%");
			}
			String date = Criteria.getDate();
			if (date!=null&&!"".equals(date.trim())) {
				sql.append(" and o.times like ?");
				params.add("%"+date+"%");
			}
			int delivery = Criteria.getDelivery();
			if (delivery!=-1) {
				sql.append(" and o.delivery = ?");
				params.add(delivery);
			}
			//根据查询条件中的内容来动态的生成sql
		}
		sql.append(" ORDER BY o.times DESC");
		try {
			dbUtil.getQueryPage(OrderInfo.class, sql.toString(), params.toArray(), page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return page;
	}

	
}
