package com.jboa.pojo;

import java.io.Serializable;
import java.util.Date;

public class CheckResult implements Serializable {
	private static final long serialVersionUID = 8755093375655169137L;
	private Integer id;
	private ClaimVoucher claimVoucher;
	private Date checkTime;
	private Employee checker;
	private String result;
	private String comment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}
	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Employee getChecker() {
		return checker;
	}
	public void setChecker(Employee checker) {
		this.checker = checker;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
