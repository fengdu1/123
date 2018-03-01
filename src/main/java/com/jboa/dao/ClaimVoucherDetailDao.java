/**
 * 
 */
package com.jboa.dao;

import java.util.List;

import com.jboa.pojo.ClaimVoucherDetail;

/**
 * 报销单详情数据访问接口
 */
public interface ClaimVoucherDetailDao {

	/**
	 * 保存报销单详情对象
	 * @param detail 报销单详情对象
	 */
	void save(ClaimVoucherDetail detail);

	/**
	 * 按报销单ID查询报销单详情数据
	 * @param claimVoucherId 报销单ID
	 * @return 报销单详情列表
	 */
	List<ClaimVoucherDetail> listByClaimVoucherId(Integer claimVoucherId);

	/**
	 * 修改报销单详情对象
	 * @param detail 报销单详情对象
	 * @return 修改影响报销单详情表的行数
	 */
	int update(ClaimVoucherDetail detail);

	/**
	 * 按报销单ID删除对应的报销单明细信息
	 * @param id 报销单ID
	 * @return 删除影响报销单详情表的行数
	 */
	int remove(Integer id);
}
