package com.qushida.po;
//admin表 对应的实体类
public class Admin {

	//属性
	private int id;//id
	private String name;//用户名
	private String pwd;//用户密码
	private String  authority;//用户权限
	//get\set方法
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	//有参构造
	public Admin(int id, String name, String pwd, String authority) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.authority = authority;
	}
	//无参构造
	public Admin() {
		super();
	}
	//重写toString
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", pwd=" + pwd + ", authority=" + authority + "]";
	}
	
	
}
