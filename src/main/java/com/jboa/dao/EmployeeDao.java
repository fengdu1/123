package com.jboa.dao;

import com.jboa.pojo.Employee;

/**
 * 员工数据访问接口
 */
public interface EmployeeDao {
	/**
	 * 按工号查询员工对象
	 * @param sn 工号
	 * @return 员工对象，如果工号不存在，返回null
	 */
	public Employee get(String sn);

	public Employee getManagerByStaff(String sn);
	
	public Employee getGeneralManager(Integer departmentId);
	
	public Employee getCashier();
}
