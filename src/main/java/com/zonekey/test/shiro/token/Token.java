package com.zonekey.test.shiro.token;

import java.util.Arrays;

import org.apache.shiro.authc.UsernamePasswordToken;

public class Token extends UsernamePasswordToken {
	private static final long serialVersionUID = 1L;

	// 验证码
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Token() {
		super();
	}

	/**
	 * 创建带验证码的Token
	 * 
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @param host
	 * @param code
	 */
	public Token(String username, String password, boolean rememberMe, String host, String code) {
		this.code = code;
		this.setHost(host);
		this.setRememberMe(rememberMe);
		this.setPassword(password.toCharArray());
		this.setUsername(username);
	}

	public Token(String username, char[] password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}

	public Token(String username, char[] password, boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public Token(String username, char[] password, String host) {
		super(username, password, host);
	}

	public Token(String username, char[] password) {
		super(username, password);
	}

	public Token(String username, String password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}

	public Token(String username, String password, boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public Token(String username, String password, String host) {
		super(username, password, host);
	}

	public Token(String username, String password) {
		super(username, password);
	}

	@Override
	public String toString() {
		return "Token [code=" + code + ", getUsername()=" + getUsername() + ", getPassword()=" + Arrays.toString(getPassword()) + ", getPrincipal()=" + getPrincipal() + ", getCredentials()="
				+ getCredentials() + ", getHost()=" + getHost() + ", isRememberMe()=" + isRememberMe() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
