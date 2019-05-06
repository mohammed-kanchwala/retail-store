package com.mk.retailstore.service;

import com.mk.retailstore.model.Customer;
import com.mk.retailstore.model.ShoppingCart;

public interface BillCalculator {

	double calculateNetPayableAmount(ShoppingCart shoppingCart, Customer customer);
}
