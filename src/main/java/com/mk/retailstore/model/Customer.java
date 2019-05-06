package com.mk.retailstore.model;

import java.util.Date;

public class Customer {

	String name;
	CustomerType customerType;
	Date joinDate;
	
	public Customer(String name, CustomerType customerType, Date joinDate) {
		this.name = name;
		this.customerType = customerType;
		this.joinDate = joinDate;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public Date getJoinDate() {
		return joinDate;
	}

}
