package com.qushida.po;
//types表对应的实体类
public class Type {
	//属性
	private int id;
	private String name;
	public Type(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type() {
		super();
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
}
