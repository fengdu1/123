/**
 * 
 */
package com.jboa.dao;

import java.util.List;

import com.jboa.pojo.CheckResult;

/**
 * 报销单审查结果数据访问接口
 */
public interface CheckResultDao {

	/**
	 * 按报销单ID查询报销单审查结果数据
	 * @param claimVoucherId 报销单ID
	 * @return 报销单审查结果列表
	 */
	List<CheckResult> listByClaimVoucherId(Integer claimVoucherId);

	/**
	 * 保存报销单审批结果
	 * @param checkResult 审批结果对象 
	 * @return 报销单审批结果保存影响数据库表的行数，返回1表示成功，否则失败
	 */
	int save(CheckResult checkResult);

	/**
	 * 按报销单ID删除审查结果信息
	 * @param id 报销单ID
	 * @return 删除影响审查结果表的行数
	 */
	int remove(Integer id);
	
}
