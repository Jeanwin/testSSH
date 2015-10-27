package com.zonekey.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zonekey.test.dao.BookDao;
import com.zonekey.test.entity.Book;
import com.zonekey.test.service.BookService;

/**
 * 不会开启锁
 * 
 * @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
 * @author admin
 * 
 */
@Service
@Transactional(readOnly = false)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Transactional(readOnly = true)
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}

	public void saveBook(Book book) {
		bookDao.saveBook(book);
	}

	public void deleteBook(int bookId) {
		bookDao.deleteBook(bookId);
	}

	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Transactional(readOnly = true)
	public Book getBookByBookId(int bookId) {
		return bookDao.getBookByBookId(bookId);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
		HibernateTemplate h = (HibernateTemplate) context.getBean("hibernateTemplate");
		System.out.println(h.find("From Book").get(0).toString());
		System.out.println(context.getBean("dataSource"));
		// BookDaoImpl boo = new BookDaoImpl();
		// System.out.println(bookDao);
		// boo.getBookByBookId(1);
	}
}