package com.zonekey.test.service.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zonekey.test.dao.impl.UserDaoImpl;
import com.zonekey.test.entity.User;
import com.zonekey.test.service.UserService;
import com.zonekey.test.util.PasswordHelper;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

	@Resource
	private UserDaoImpl userDao;
	@Resource
	private PasswordHelper passwordHelper;

	/**
	 * 使用用户名和密码登录
	 */
	@Transactional
	@Override
	public User login(String username, String password) {
		return userDao.uniqueResult(userDao.createCriteria().add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)));
	}

	/**
	 * 根据用户名查询用户
	 */
	@Transactional
	@Override
	public User get(String username) {
		return userDao.uniqueResult("username", username);
	}
}
