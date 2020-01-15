package com.qushida.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.qushida.dao.OrderDao;
import com.qushida.dao.impl.OrderDaoImpl;
import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.service.OrderService;
import com.qushida.util.DBUtil;
import com.qushida.util.Page;
import com.qushida.vo.OrderStatistic;

public class OrderServiceImpl implements OrderService {
	private  OrderDao orderDao = new  OrderDaoImpl();
	@Override
	public boolean addOrder(List<ShoppingCar> carList, String userId) {
		boolean result = false;
		//循环调用OrderDao中的addOrder方法
		//使用事务-->保证同一连接Connection
		try {
			DBUtil.beginTranscation();
			//调用循环
			//遍历List<ShoppingCar>-->每一个条目-->得到menuid,menusun,userId-->Order
			for(ShoppingCar carItem:carList){
				//carItem
				//menuid  menusum  userId-->Order
				int menuId = carItem.getMenuId();
				int sums = carItem.getSums();
				Order order = new Order();
				order.setMenuid(String.valueOf(menuId));
				order.setMenusum(String.valueOf(sums));
				order.setUserid(userId);
				//调用OrderDao中的addOrder方法
				orderDao.addOrder(order);
			}
			DBUtil.endTranscation();   //提交事务
			result = true ;
		} catch (Exception e) {
			try {
				DBUtil.rollback();    //回滚事务
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			//关闭连接
			try {
				DBUtil.closeConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Page queryOrderByPage(Page page,OrderCriteria criteria) {
		return orderDao.queryOrderByPage(page,criteria);
	}
	@Override
	public boolean deleteOrderById(int orderId) {
		
		return orderDao.deleteOrderById(orderId)>0;
	}
	@Override
	public boolean updateOrderById(int orderId) {
		return orderDao.updateOrderById(orderId)>0;
	}
	@Override
	public List<OrderStatistic> queryOrderByDelivery() {
		List<OrderStatistic> list = null;
		try{
			list = orderDao.queryOrderByDelivery();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Page queryUserOrderByPage(Page page, OrderCriteria Criteria, int id,int de) {
		return orderDao.queryUserOrderByPage(page, Criteria, id,de);
	}

	
}
