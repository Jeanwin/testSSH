package com.zonekey.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Table(name = "book", catalog = "springtest")指定数据库名称及表名 Book entity. @author
 *             MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book")
public class Book implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer bookId;
	private String name;
	private String description;
	private String author;
	private float prise;

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** full constructor */
	public Book(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Book(String name, String description, String author, float prise) {
		super();
		this.name = name;
		this.description = description;
		this.author = author;
		this.prise = prise;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 不映射字段
	 * 
	 * @return
	 */
	@Transient
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Transient
	public float getPrise() {
		return prise;
	}

	public void setPrise(float prise) {
		this.prise = prise;
	}

}