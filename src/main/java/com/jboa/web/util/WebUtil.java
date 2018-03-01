package com.jboa.web.util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public abstract class WebUtil {
	public static void setSession(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSession(String key) {
		return (T)ActionContext.getContext().getSession().get(key);
	}
	
	public static void removeSession(String key) {
		ActionContext.getContext().getSession().remove(key);
	}
	
	public static void setRequest(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
	
	public static String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	
	public static void invalidate(){
		ServletActionContext.getRequest().getSession().invalidate();
	}
}
