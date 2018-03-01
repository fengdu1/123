/**
 * 
 */
package com.jboa.dao.impl;

import java.sql.ResultSet; 
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jboa.dao.BaseDao;
import com.jboa.dao.EmployeeDao;
import com.jboa.pojo.Department;
import com.jboa.pojo.Employee;
import com.jboa.pojo.Position;

/**
 * 员工数据访问接口的mysql实现类
 * @version 1.0
 */
//@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
	private static final Logger LOG = LogManager.getLogger(EmployeeDaoImpl.class);

	@Override
	public Employee get(String sn) {
		StringBuilder builder = new StringBuilder();
		builder
		.append("select e.*, p.*, d.name departmentName")
		.append(" from sys_employee e")
		.append(" inner join sys_position p")
		.append(" on p.id = e.positionId")
		.append(" inner join sys_department d")
		.append(" on d.id = e.departmentId")
		.append(" where e.sn = ? and e.status = '在职'");
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + sn);
		
		return getJdbcTemplate().query(builder.toString(), new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				Employee employee = null;
				if (rs.next()) {
					Position position = new Position(
						rs.getInt("id"),
						rs.getString("namecn"),
						rs.getString("nameen")
					);
					Department department = new Department(
						rs.getInt("departmentId"),
						rs.getString("departmentName")
					);
					employee = new Employee(
						rs.getString("sn"),
						position,
						department,
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("status")
					);
				}
				return employee;
			}
		}, sn);
	}
	
	@Override
	public Employee getManagerByStaff(String sn) {
		StringBuilder builder = new StringBuilder();
		builder
		.append("select e.*, p.*, d.name departmentName")
		.append(" from sys_employee e")
		.append(" inner join sys_position p")
		.append(" on p.id = e.positionId")
		.append(" inner join sys_department d")
		.append(" on d.id = e.departmentId")
		.append(" where e.departmentId = (")
		.append(" 	select departmentId from sys_employee where sn = ?")
		.append(" ) and e.positionId = (")
		.append(" 	select id from sys_position where nameen = 'manager'")
		.append(" ) and status = '在职'");
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + sn);
		
		return getJdbcTemplate().queryForObject(builder.toString(), new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Position position = new Position(
					rs.getInt("id"),
					rs.getString("namecn"),
					rs.getString("nameen")
				);
				Department department = new Department(
					rs.getInt("departmentId"),
					rs.getString("departmentName")
				);
				return new Employee(
					rs.getString("sn"),
					position,
					department,
					rs.getString("name"),
					rs.getString("password"),
					rs.getString("status")
				);
			}
		}, sn);
	}

	@Override
	public Employee getGeneralManager(Integer departmentId) {
		StringBuilder builder = new StringBuilder();
		builder
		.append("select e.*, p.*, d.name departmentName")
		.append(" from sys_employee e")
		.append(" inner join sys_position p")
		.append(" on p.id = e.positionId")
		.append(" inner join sys_department d")
		.append(" on d.id = e.departmentId")
		.append(" where e.positionId = (")
		.append(" 	select id from sys_position where nameen = 'generalmanager'")
		.append(" ) and e.departmentId = ?")
		.append(" and e.status = '在职'");
		
		LOG.debug("T-SQL：" + builder.toString());
		
		return getJdbcTemplate().query(builder.toString(), new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				Employee employee = null;
				if (rs.next()) {
					Position position = new Position(
						rs.getInt("id"),
						rs.getString("namecn"),
						rs.getString("nameen")
					);
					Department department = new Department(
						rs.getInt("departmentId"),
						rs.getString("departmentName")
					);
					employee = new Employee(
						rs.getString("sn"),
						position,
						department,
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("status")
					);
				}
				return employee;
			}
		},departmentId);
	}

	@Override
	public Employee getCashier() {
		StringBuilder builder = new StringBuilder();
		builder
		.append("select e.*, p.*, d.name departmentName")
		.append(" from sys_employee e")
		.append(" inner join sys_position p")
		.append(" on p.id = e.positionId")
		.append(" inner join sys_department d")
		.append(" on d.id = e.departmentId")
		.append(" where e.positionId = (")
		.append(" 	select id from sys_position where nameen = 'cashier'")
		.append(" ) and e.status = '在职'");
		
		LOG.debug("T-SQL：" + builder.toString());
		
		return getJdbcTemplate().query(builder.toString(), new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				Employee employee = null;
				if (rs.next()) {
					Position position = new Position(
						rs.getInt("id"),
						rs.getString("namecn"),
						rs.getString("nameen")
					);
					Department department = new Department(
						rs.getInt("departmentId"),
						rs.getString("departmentName")
					);
					employee = new Employee(
						rs.getString("sn"),
						position,
						department,
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("status")
					);
				}
				return employee;
			}
		});
	}
}
