package com.zonekey.test.dao.test;

import org.hibernate.Criteria;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.zonekey.test.common.SpringTxTestCase;
import com.zonekey.test.dao.base.Page;
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
		bookDao.exists(1);
		bookDao.deleteBook(1);
		bookDao.get(1);
		bookDao.list();
		bookDao.saveOrUpdate(new Book("简爱", "a good book"));
		System.out.println("count:"+bookDao.count(true, "select count(*) from Book", null));
		Criteria c = bookDao.createCriteria();
		System.out.println(bookDao.pagedQuery(c, 1, 2).toString());
		Page page = bookDao.pagedQuery(true, "from Book", "select count(*) from Book", new Page(1,2,null));
		System.out.println("page:"+page.toString());
	}
}
