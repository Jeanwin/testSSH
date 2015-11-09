package com.zonekey.test.service;

import com.zonekey.test.entity.User;

public interface UserService {
	public User login(String username, String password);

	public User get(String username);
}
