package com.jboa.pojo;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {
	private static final long serialVersionUID = 226689637263915815L;
	private Integer id;
	private Employee employee;
	private Date startTime;
	private Date endTime;
	private Double leaveDay;
  	private String reason;
	private String status;
	private String leaveType;
  	private Employee nextDeal;
	private String approveOpinion;
 	private Date createTime;
	private Date modifyTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(Double leaveDay) {
		this.leaveDay = leaveDay;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Employee getNextDeal() {
		return nextDeal;
	}
	public void setNextDeal(Employee nextDeal) {
		this.nextDeal = nextDeal;
	}
	public String getApproveOpinion() {
		return approveOpinion;
	}
	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
