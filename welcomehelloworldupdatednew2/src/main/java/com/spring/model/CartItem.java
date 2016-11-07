package com.spring.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cartitem")
public class CartItem implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int cartItemId;
private int quantity;
private double TotalPrice;
@ManyToOne
@JoinColumn(name="isbn")
private Book book;
@ManyToOne
@JoinColumn(name="cartid")
@JsonIgnore
private Cart cart;
public int getCartItemId() {
	return cartItemId;
}
public void setCartItemId(int cartItemId) {
	this.cartItemId = cartItemId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getTotalPrice() {
	return TotalPrice;
}
public void setTotalPrice(double price) {
	this.TotalPrice = price;
}
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
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


