/**
 * 
 */
package com.jboa.util;

/**
 * 业务常量类
 * 
 */
public abstract class BusinessConstants {
	/**
	 * 员工
	 */
	public static final String EMPLOYEE_STAFF = "staff";
	/**
	 * 部门经理
	 */
	public static final String EMPLOYEE_MANAGER = "manager";
	/**
	 * 总经理
	 */
	public static final String EMPLOYEE_GENERAL_MANAGER = "generalmanager";
	/**
	 * 财务
	 */
	public static final String EMPLOYEE_CASHIER = "cashier";

	/**
	 * 新创建
	 */
	public static final String NEW_CREATE = "新创建";
	/**
	 * 已提交
	 */
	public static final String ALREADY_COMMIT = "已提交";
	/**
	 * 待审批
	 */
	public static final String WAIT_EXAMINE = "待审批";
	/**
	 * 已打回
	 */
	public static final String ALREADY_RETURN = "已打回";
	/**
	 * 已审批
	 */
	public static final String ALREADY_EXAMINE = "已审批";
	/**
	 * 已付款
	 */
	public static final String ALREADY_PAYMENT = "已付款";
	/**
	 * 已终止
	 */
	public static final String ALREADY_TERMINATION = "已终止";
	
	/* 报销单审批结果 */
	/**
	 * 通过 
	 */
	public static final String CHECK_PASS = "通过";
	/**
	 * 打回
	 */
	public static final String CHECK_BACK = "打回";
	/**
	 * 拒绝
	 */
	public static final String CHECK_REFUSE = "拒绝";
	/**
	 * 付款
	 */
	public static final String CHECK_PAYMENT = "付款";
}
