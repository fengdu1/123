package com.jboa.web.interceptors;

import org.springframework.stereotype.Component;

import com.jboa.pojo.Employee;
import com.jboa.web.util.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@Component("loginInterceptor")
public class LoginInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 2799866070892876746L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Employee loginUser = (Employee)invocation.getInvocationContext().getSession().get(Constants.LOGIN_EMPLOYEE);
		if (loginUser == null) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}
}
