package com.mk.retailstore.service;

import com.mk.retailstore.model.Customer;

public interface DiscountStrategy {

	int calculateDiscountStrategy (Customer  customer);
}
