package com.zonekey.test.dao.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.zonekey.test.common.SpringTxTestCase;
import com.zonekey.test.dao.impl.BookDaoImpl;
import com.zonekey.test.entity.Book;

@ContextConfiguration(locations = { "/spring/spring*.xml" })
public class BookDaoTest extends SpringTxTestCase {
	private static final Logger LOG = LoggerFactory.getLogger(BookDaoTest.class);
	@Autowired
	private BookDaoImpl bookDao;

	public void setBookDao(BookDaoImpl bookDao) {
		this.bookDao = bookDao;
	}

	@Test
	public void test() {
		LOG.info("BookDaoTest start");
		// BookDaoImpl bookDao = new BookDaoImpl();
		System.out.println(bookDao.getBookByBookId(1).getName());
		bookDao.getBookByBookId(1);
		bookDao.getBookList();
		Book book = new Book();
		book.setDescription("钢铁是怎样炼成的--保尔柯察金");
		book.setName("钢铁是怎样炼成的");
		bookDao.saveBook(book);
	}
}
