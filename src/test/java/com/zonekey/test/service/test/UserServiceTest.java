package com.zonekey.test.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zonekey.test.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring*.xml" })
@Transactional
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;

	@Test
	public void test() {
		System.out.println("user1:"+userService.get("zhangsan"));
		System.out.println("user2:"+userService.login("zhangsan", "123456"));
	}

}
