package com.jboa.service;

import com.jboa.pojo.Employee;

public interface EmployeeService {
	/**
	 * 按工号查询员工对象
	 * @param sn 工号
	 * @return 员工对象，如果工号不存在，返回null
	 */
	public Employee getEmployeeBySn(String sn);
}
