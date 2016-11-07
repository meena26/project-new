package com.spring.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="cart")
public class Cart implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
private int cartId;
@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
private List<CartItem> cartItem;
@OneToOne
@JoinColumn(name="customerId")
@JsonIgnore
private Customer customer;
private double grandTotal;

public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public double getGrandTotal() {
	return grandTotal;
}
public void setGrandTotal(double grandTotal) {
	this.grandTotal = grandTotal;
}

//public double getTotalPrice() {
//	return totalPrice;
//}
//public void setTotalPrice(double totalPrice) {
//	this.totalPrice = totalPrice;
//}
public List<CartItem>  getCartItems() {
	return cartItem;
}
public void setCartItems(List<CartItem> cartItem) {
	this.cartItem = cartItem;
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



