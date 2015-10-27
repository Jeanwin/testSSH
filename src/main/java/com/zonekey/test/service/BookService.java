package com.zonekey.test.service;

import java.util.List;

import com.zonekey.test.entity.Book;
public interface BookService {
	public List<Book> getBookList();

	public void saveBook(Book book);

	public void deleteBook(int bookId);

	public void updateBook(Book book);

	public Book getBookByBookId(int bookId);
}