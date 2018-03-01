package com.jboa.pojo;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = -3320929373516766087L;
	private Integer id;
	private String namecn;
	private String nameen;
	
	public Position() {
		
	}
	
	public Position(Integer id, String namecn, String nameen) {
		this.id = id;
		this.namecn = namecn;
		this.nameen = nameen;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNamecn() {
		return namecn;
	}
	public void setNamecn(String namecn) {
		this.namecn = namecn;
	}
	public String getNameen() {
		return nameen;
	}
	public void setNameen(String nameen) {
		this.nameen = nameen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
