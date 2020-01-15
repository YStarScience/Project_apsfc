package com.qushida.dao;

import java.util.List;

import com.qushida.po.Order;
import com.qushida.po.OrderCriteria;
import com.qushida.util.Page;
import com.qushida.vo.OrderInfo;
import com.qushida.vo.OrderStatistic;

/**
 * 订单表的接口
 * @author administrator
 *
 */
public interface OrderDao {
	//添加一条记录
	//insert into orders (userid,menuid,menusum,times,delivery) values(?,?,?,sysdate(),0);
	int addOrder(Order order) throws Exception;
//	//查询登陆用户的所有订单
//	List<OrderInfo> getAllOrderByUserId(int userId);
//	//分页查询订单
	//参数：Page-->(当前页，一页几条）  四个条件
	//返回值：Page
	Page queryOrderByPage(Page page,OrderCriteria criteria);
	
	Page queryUserOrderByPage(Page page,OrderCriteria Criteria,int id,int de);
	//根据订单id删除订单-->delete from orders where id = ?
	//参数--》订单id
	//返回值-->受影响的行数
	int deleteOrderById(int orderId);
	//根据订单id修改订单-->update orders set delivery = '1' where id=?
	//参数：订单id
	//返回值：受影响行数
	int updateOrderById(int orderId);
	List<OrderStatistic> queryOrderByDelivery();
	
}
