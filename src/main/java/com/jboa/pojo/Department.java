package com.jboa.pojo;

import java.io.Serializable;

public class Department implements Serializable {
	private static final long serialVersionUID = -5093428068250708163L;
	private Integer id;
	private String name;
	public Department() {
	}
	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
