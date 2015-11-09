package com.zonekey.test.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.zonekey.test.dao.impl.BookDaoImpl;
import com.zonekey.test.entity.Book;

/**
 * 
 * Struts2基于注解的Action配置
 * 
 * ParentPackage 继承父包 Namespace命名空间 Results跳转页面 Action访问方法
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/book")
@Results({ @Result(name = "success", location = "/views/main.jsp"), @Result(name = "error", location = "/views/error.jsp") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class BookAction extends ActionSupport {
	private static final long serialVersionUID = 2954424120917891060L;
	private String username;
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Resource
	private BookDaoImpl bookDao;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Action(value = "test")
	public String test() {
		this.setUsername("strutsTest");
		return SUCCESS;
	}

	@Action(value = "bookView", results = { @Result(name = "success", location = "/index.jsp") })
	public String bookView() {
		books = (List<Book>) bookDao.getBookList();
		return SUCCESS;
	}
}