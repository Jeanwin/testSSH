package com.zonekey.test.dao.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.zonekey.test.common.SpringTxTestCase;
import com.zonekey.test.dao.impl.TreeDaoImpl;

@ContextConfiguration(locations = { "/spring/spring*.xml" })
public class TreeDaoTest extends SpringTxTestCase {
	@Resource
	private TreeDaoImpl treeDao;

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		System.out.println(treeDao.list());
		long duration = System.currentTimeMillis() - start;
		System.out.println("it takes " + duration + "ms");
	}

	@Test
	public void test1() {
       System.out.println("count:"+treeDao.count());
       treeDao.delete(2);       
       System.out.println("byId:"+treeDao.get(2));
	}
}
