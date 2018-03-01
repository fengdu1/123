package com.jboa.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClaimVoucher implements Serializable {
	private static final long serialVersionUID = -3924121562537730521L;
	private Integer id;
  	private Employee nextDeal;
  	private Employee creator;
  	private Date createTime;
	private String event;
	private Double totalAccount;
	private String status;
	private Date modifyTime;
	private List<ClaimVoucherDetail> claimVoucherDetails;
	private List<CheckResult> checkResults;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getNextDeal() {
		return nextDeal;
	}
	public void setNextDeal(Employee nextDeal) {
		this.nextDeal = nextDeal;
	}
	public Employee getCreator() {
		return creator;
	}
	public void setCreator(Employee creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Double getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public List<ClaimVoucherDetail> getClaimVoucherDetails() {
		return claimVoucherDetails;
	}
	public void setClaimVoucherDetails(List<ClaimVoucherDetail> claimVoucherDetails) {
		this.claimVoucherDetails = claimVoucherDetails;
	}
	public List<CheckResult> getCheckResults() {
		return checkResults;
	}
	public void setCheckResults(List<CheckResult> checkResults) {
		this.checkResults = checkResults;
	}
	public Double calcTotalAccount() {
		if (claimVoucherDetails == null || claimVoucherDetails.size() == 0) 
			return Double.valueOf(0);
		BigDecimal result = new BigDecimal(String.valueOf(0));
		for (ClaimVoucherDetail detail : claimVoucherDetails) {
			result = result.add(new BigDecimal(String.valueOf(detail.getAccount())));
		}
		return result.doubleValue();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
