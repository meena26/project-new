package com.spring.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="customer")
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int customerId;
private String customerName;
private String customerEmail;
private String customerPhone;

@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="usersid")
private Users users;

@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="billingaddressid")
private BillingAddress billingAddress;

@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="shippingaddressid")

private ShippingAddress shippingAddress;
@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="cartId")
@JsonIgnore
private Cart cart;
public Cart getCart() {
		return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}

public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getCustomerEmail() {
	return customerEmail;
}
public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}
public String getCustomerPhone() {
	return customerPhone;
}
public void setCustomerPhone(String customerPhone) {
	this.customerPhone = customerPhone;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public BillingAddress getBillingAddress() {
	return billingAddress;
}
public void setBillingAddress(BillingAddress billingAddress) {
	this.billingAddress = billingAddress;
}
public ShippingAddress getShippingAddress() {
	return shippingAddress;
}
public void setShippingAddress(ShippingAddress shippingAddress) {
	this.shippingAddress = shippingAddress;
}
//@OneToOne
//@JoinColumn(name="cartid")
//private Cart cart;
//public Cart getCart() {
	//	return cart;
//}
//public void setCart(Cart cart) {
//	this.cart = cart;


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





