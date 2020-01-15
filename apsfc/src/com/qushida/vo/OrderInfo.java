package com.qushida.vo;

public class OrderInfo {
	private int orderId;
	private int userId;
	private String realName;
	private String phone;
	private String address;
	private String menuName;
	private String menuSum;
	private String price1;
	private String times;
	private String delivery;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuSum() {
		return menuSum;
	}
	public void setMenuSum(String menuSum) {
		this.menuSum = menuSum;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public OrderInfo(int orderId, int userId, String realName, String phone, String address, String menuName,
			String menuSum, String price1, String times, String delivery) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.realName = realName;
		this.phone = phone;
		this.address = address;
		this.menuName = menuName;
		this.menuSum = menuSum;
		this.price1 = price1;
		this.times = times;
		this.delivery = delivery;
	}
	public OrderInfo() {
		super();
	}
	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", userId=" + userId + ", realName=" + realName + ", phone=" + phone
				+ ", address=" + address + ", menuName=" + menuName + ", menuSum=" + menuSum + ", price1=" + price1
				+ ", times=" + times + ", delivery=" + delivery + "]";
	}

}
