package com.qushida.service;

import java.util.List;

import com.qushida.po.OrderCriteria;
import com.qushida.po.ShoppingCar;
import com.qushida.util.Page;
import com.qushida.vo.OrderStatistic;

public interface OrderService {

	// 下单
	// 参数:购物车/用户id
	// 返回值:boolean
	boolean addOrder(List<ShoppingCar> carList, String userId);

	// 分页查询所有订单
	Page queryOrderByPage(Page page,OrderCriteria criteria);

	Page queryUserOrderByPage(Page page,OrderCriteria Criteria,int id,int de);
	// 根据订单id删除订单
	boolean deleteOrderById(int orderId);
	
	// 根据订单id修改订单
	boolean updateOrderById(int orderId);

	List<OrderStatistic> queryOrderByDelivery();

}
