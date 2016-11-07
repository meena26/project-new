package com.spring.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="authorities")
public class Authorities implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private  int authoritiesId;
private String username;
private String authorities;
public int getAuthoritiesId() {
	return authoritiesId;
}
public void setAuthoritiesId(int authoritiesId) {
	this.authoritiesId = authoritiesId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAuthorities() {
	return authorities;
}
public void setAuthorities(String authorities) {
	this.authorities = authorities;
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

	

