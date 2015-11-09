package com.zonekey.test.dao.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zonekey.test.dao.impl.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring*.xml" })
@Transactional
public class UserDaoTest {
	@Resource
	private UserDaoImpl userDao;
	@Test
	public void test() {
		System.out.println(userDao.get(1));
	}

}
