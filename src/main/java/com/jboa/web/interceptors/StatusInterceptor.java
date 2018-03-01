package com.jboa.web.interceptors;

import org.springframework.stereotype.Component;

import com.jboa.web.util.WebUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component("statusInterceptor")
public class StatusInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 2799866070892876746L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println(invocation.getProxy().getActionName());
		String _status = invocation.getInvocationContext().getParameters().get("_status").getValue();
		if (_status != null) {
			WebUtil.setSession("_status", _status);
		}
		return invocation.invoke();
	}
}
