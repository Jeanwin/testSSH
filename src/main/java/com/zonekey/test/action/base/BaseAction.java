package com.zonekey.test.action.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 3687279856140081759L;

	public static final String LIST = "list";

	public static final String NEXT = "next";

	String inputAction;

	String nextAction;

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}

	public String getInputAction() {
		return inputAction;
	}

	public void setInputAction(String inputAction) {
		this.inputAction = inputAction;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

}
