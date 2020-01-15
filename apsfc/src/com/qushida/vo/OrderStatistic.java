package com.qushida.vo;

public class OrderStatistic {
	private String name;
	private double sums1;
	private String price1;
	private double total;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSums1() {
		return sums1;
	}
	public void setSums1(double sums1) {
		this.sums1 = sums1;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public OrderStatistic(String name, int sums1, String price1, double total) {
		super();
		this.name = name;
		this.sums1 = sums1;
		this.price1 = price1;
		this.total = total;
	}
	public OrderStatistic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
