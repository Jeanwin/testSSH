package com.zonekey.test.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zonekey.test.dao.BookDao;
import com.zonekey.test.dao.base.impl.HibernateBaseDaoImpl;
import com.zonekey.test.entity.Book;

@Repository
public class BookDaoImpl extends HibernateBaseDaoImpl<Book, Integer> implements BookDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public List<Book> getBookList() {
		@SuppressWarnings("unchecked")
		List<Book> list = (List<Book>) getHibernateTemplate().find("From Book");
		return list;
	}

	public void saveBook(Book book) {
		getHibernateTemplate().save(book);

	}

	public void deleteBook(int bookId) {
		getHibernateTemplate().delete(getBookByBookId(bookId));

	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(book);
	}

	public Book getBookByBookId(int bookId) {
		Book book = (Book) this.hibernateTemplate.get(Book.class, bookId);
		return book;
	}

}