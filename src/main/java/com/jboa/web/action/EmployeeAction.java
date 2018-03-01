package com.jboa.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.web.util.Constants;
import com.jboa.web.util.WebUtil;
import com.jboa.pojo.Employee;
import com.jboa.service.EmployeeService;

@Controller
@Scope("prototype")
@ParentPackage("default")
public class EmployeeAction extends BaseAction<Employee> {
	private static final long serialVersionUID = 2633528518835114675L;
	@Autowired
	private EmployeeService employeeService;
	
	@Action(value="login",results={
			@Result(type="redirectAction",location="home"),
			@Result(name="input",location="/login.jsp")
	})
	public String login(){
		Employee employee = employeeService.getEmployeeBySn(getModel().getSn());
		if (employee == null) {//工号不存在
			addFieldError("sn", getText("employee.sn.notexist"));
		} else if (employee.getPassword().equals(getModel().getPassword())) {
			WebUtil.setSession(Constants.LOGIN_EMPLOYEE, employee);
			return SUCCESS;
		} else {//密码错误
			addFieldError("password", getText("employee.password.error"));
		}
		return INPUT;
	}
	
		//注销
		@Action(value="logout", results={
			@Result(type="redirect", location="/login.jsp"),
		})
		public String logout() throws Exception {
			//WebUtil.removeSession(Constants.LOGIN_EMPLOYEE);
			WebUtil.invalidate();
			return SUCCESS;
		}
		
		//导航到欢迎页
		@Action(value="home", results={
			@Result(location="/WEB-INF/views/home.jsp")
		})
		public String home() throws Exception {
			return SUCCESS;
		}
}
