package com.zonekey.test.dao.impl;

import com.zonekey.test.dao.UserDao;
import com.zonekey.test.dao.base.impl.HibernateBaseDaoImpl;
import com.zonekey.test.entity.User;
import com.zonekey.test.util.HibernateRepoistory;

@HibernateRepoistory
public class UserDaoImpl extends HibernateBaseDaoImpl<User, Integer> implements UserDao {

}
