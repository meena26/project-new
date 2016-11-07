package com.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
  @Entity
	@Table(name="bookcategories")
	public class Category implements Serializable { // Category has list of Books
	@Id
	private int cid;
	@Column(name="category")
	private String categoryName;
	@OneToMany(mappedBy="category")
	List<Book> books;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override 
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	@Override 
	public boolean equals(Object obj){
		return EqualsBuilder.reflectionEquals(this,obj);
	}
	@Override 
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
	}
	}





