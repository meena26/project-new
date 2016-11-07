package com.spring.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="users")
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int userId;
private String username;
private String password;
private boolean enabled;

@OneToOne(mappedBy="users")
private Customer customer;


public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
public Customer getCustomer() {
	return customer;
}
public  void setCustomer(Customer customer) {
	this.customer = customer;
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





