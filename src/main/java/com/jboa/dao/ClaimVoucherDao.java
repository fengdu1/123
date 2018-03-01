package com.jboa.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jboa.pojo.ClaimVoucher;

/**
 * 报销单数据访问接口
 */
public interface ClaimVoucherDao {

	/**
	 * 查询员工的报销单总数
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 符合条件的报销单总数
	 */
	Integer countByStaff(
		@Param("claimVoucher") ClaimVoucher claimVoucher, 
		@Param("beginTime") Date beginTime, 
		@Param("endTime") Date endTime);

	/**
	 * 查询部门经理的报销单总数
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 符合条件的报销单总数
	 */
	Integer countByManager(
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 查询总经理的报销单总数
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 符合条件的报销单总数
	 */
	Integer countByGeneralManager(
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 查询财务的报销单总数
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 符合条件的报销单总数
	 */
	Integer countByCashier(
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 分页查询查询员工的报销单数据
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 报销单列表
	 */
	List<ClaimVoucher> listByStaff(
		@Param("begin") Integer begin, 
		@Param("size") Integer size, 
		@Param("claimVoucher") ClaimVoucher claimVoucher, 
		@Param("beginTime") Date beginTime, 
		@Param("endTime") Date endTime);

	/**
	 * 分页查询查询部门经理的报销单数据
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 报销单列表
	 */
	List<ClaimVoucher> listByManager(
			@Param("begin") Integer begin, 
			@Param("size") Integer size, 
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 分页查询查询总经理的报销单数据
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 报销单列表
	 */
	List<ClaimVoucher> listByGeneralManager(
			@Param("begin") Integer begin, 
			@Param("size") Integer size, 
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 分页查询查询财务的报销单数据
	 * @param pageIndex 页码
	 * @param pageSize 页大小
	 * @param claimVoucher 封装了查询条件的报销单对象
	 * @param beginTime 报销单创建时间的起始时间
	 * @param endTime 报销单创建时间的终止时间
	 * @return 报销单列表
	 */
	List<ClaimVoucher> listByCashier(
			@Param("begin") Integer begin, 
			@Param("size") Integer size, 
			@Param("claimVoucher") ClaimVoucher claimVoucher, 
			@Param("beginTime") Date beginTime, 
			@Param("endTime") Date endTime);

	/**
	 * 保存报销单对象，自动填充报销单对象的ID
	 * @param claimVoucher 被保存的报销单对象
	 * @return 保存报销单数据影响数据库表的行数，1表示成功，否则表示失败
	 */
	int save(ClaimVoucher claimVoucher);

	/**
	 * 查找报销单对象
	 * @param id 报销单ID
	 * @return 报销单对象，如果报销单ID不存在，返回null
	 */
	ClaimVoucher get(Integer id);

	/**
	 * 修改报销单对象
	 * @param claimVoucher 被修改的报销单对象
	 * @return 修改影响报销单表的行数
	 */
	int update(ClaimVoucher claimVoucher);

	/**
	 * 修改报销单状态为“已提交”
	 * @param id 报销单ID
	 * @return 修改影响报销单表的行数
	 */
	int update(Integer id);

	/**
	 * 按报销单ID删除报销单数据
	 * @param id 报销单ID
	 * @return 删除影响报销单表的行数
	 */
	int remove(Integer id);

}
