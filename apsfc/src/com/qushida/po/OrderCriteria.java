package com.qushida.po;
//查询信息类
public class OrderCriteria {
	private int userId;
	private String menuName;
	private String date;
	private int delivery=-1;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public OrderCriteria(int userId, String menuName, String date, int delivery) {
		super();
		this.userId = userId;
		this.menuName = menuName;
		this.date = date;
		this.delivery = delivery;
	}
	public OrderCriteria() {
		super();
	}
	@Override
	public String toString() {
		return "OrderCriteria [userId=" + userId + ", menuName=" + menuName + ", date=" + date + ", delivery="
				+ delivery + "]";
	}
	
}
