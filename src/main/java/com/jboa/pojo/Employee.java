package com.jboa.pojo;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 7635206956745393095L;
	private String sn;
	private Position position;
	private Department department;
	private String name;
	private String password;
	private String status;
	
	public Employee() {

	}
	
	public Employee(String sn, Position position, Department department, String name, String password, String status) {
		super();
		this.sn = sn;
		this.position = position;
		this.department = department;
		this.name = name;
		this.password = password;
		this.status = status;
	}

	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
