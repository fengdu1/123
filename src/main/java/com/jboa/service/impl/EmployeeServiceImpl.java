package com.jboa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboa.dao.EmployeeDao;
import com.jboa.pojo.Employee;
import com.jboa.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public Employee getEmployeeBySn(String sn) {
		return employeeDao.get(sn);
	}

}
