package com.zonekey.test.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zonekey.test.entity.Book;
import com.zonekey.test.service.impl.BookServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring*.xml" })
@Transactional
public class BookServiceTest {
	@Autowired
	private BookServiceImpl bookService;

	@Test
	public void test() {
		bookService.saveBook(new Book("javascript", "this is javascript!"));
	}

}
