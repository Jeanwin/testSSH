package com.zonekey.test.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.zonekey.test.action.base.BaseAction;
import com.zonekey.test.entity.User;
import com.zonekey.test.shiro.token.Token;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Results({ @Result(name = "failure", location = "failure.jsp"), @Result(name = "error", location = "error.jsp") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -1132451347359365419L;
	private User user;

	/**
	 * 打开登录页
	 * 
	 * @return
	 */
	@Action(value = "ol", results = { @Result(name = LOGIN, location = "login.jsp") })
	public String openLogin() {
		return LOGIN;
	}

	/**
	 * 登录控制器 必须有权限，否则会重定向到/login 不能加"/"<br>
	 * Token必须和Realm中的Token一致
	 * 
	 * @return
	 */
	@Action(value = "login", results = { @Result(name = LOGIN, location = "ol", type = "redirectAction"), @Result(name = SUCCESS, location = "index.jsp", type = "redirect") })
	public String exec() {
		System.out.println(user);
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			try {
				subject.login(new Token(user.getUsername(), user.getPassword(), user.isRememberMe(), this.getRequest().getRemoteHost()));
				if (subject.isAuthenticated()) {
					return SUCCESS;
				} else {
					this.getRequest().setAttribute("message", "用户名或密码错误");
					return LOGIN;
				}

			} catch (UnknownAccountException e1) {
				this.getRequest().setAttribute("message", "该用户不存在！");
				return LOGIN;
			} catch (LockedAccountException e2) {
				this.getRequest().setAttribute("message", "当前用户已被锁定");
				return LOGIN;
			} catch (ExcessiveAttemptsException e3) {
				this.getRequest().setAttribute("message", "密码出错超过5次，请10分钟后重试");
				return LOGIN;
			} catch (Exception e4) {
				this.getRequest().setAttribute("message", "登录出错，请重试");
				return LOGIN;
			}
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 会在action执行之前执行 执行数据校验的validate方法
	 */
	/*
	 * public void validate() { // 如果用户名为空，或者为空字符串 if (user.getUsername() ==
	 * null || user.getUsername().trim().equals("")) { //
	 * 添加校验错误提示，使用getText方法来使提示信息国际化 addFieldError("username",
	 * getText("user.required")); } if (user.getPassword() == null ||
	 * user.getPassword().trim().equals("")) { addFieldError("password",
	 * getText("pass.required")); } }
	 */

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
