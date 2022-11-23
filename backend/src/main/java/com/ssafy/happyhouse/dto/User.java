package com.ssafy.happyhouse.dto;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private String tel;
	private boolean isLogin;
	
	public User() {
		super();
	}
	public User(String id, String pwd, String name, String addr, String tel) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", addr=" + addr + ", tel=" + tel + ", isLogin="
				+ isLogin + "]";
	}
}
