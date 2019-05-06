package com.mk.retailstore.service.impl;

import static com.mk.retailstore.service.impl.BillCalculatorTest.getDateWithYear;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mk.retailstore.RetailStoreApplicationTests;
import com.mk.retailstore.model.Customer;
import com.mk.retailstore.model.CustomerType;
import com.mk.retailstore.service.DiscountStrategy;

public class DiscountStrategyTest extends RetailStoreApplicationTests {

	@Autowired
	private DiscountStrategy discountStrategy;

	@Test
	public void test_EmployeeDiscountStrategy() {
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-1));
		assertEquals(30, discountStrategy.calculateDiscountStrategy(customer));
	}

	@Test
	public void test_AffiliatedDiscountStrategy() {
		Customer customer = new Customer("Customer Name", CustomerType.AFFILIATED, getDateWithYear(-2));
		assertEquals(10, discountStrategy.calculateDiscountStrategy(customer));
	}

	@Test
	public void test_RegularDiscountStrategy_LessThan2Years() {
		Customer customer = new Customer("Customer Name", CustomerType.REGULAR, getDateWithYear(-1));
		assertEquals(0, discountStrategy.calculateDiscountStrategy(customer));
	}

	@Test
	public void test_RegularDiscountStrategy_MoreThan2Years() {
		Customer customer = new Customer("Customer Name", CustomerType.REGULAR, getDateWithYear(-5));
		assertEquals(5, discountStrategy.calculateDiscountStrategy(customer));
	}

	@Test
	public void test_RegularDiscountStrategy_EqualTo2Years() {
		Customer customer = new Customer("Customer Name", CustomerType.REGULAR, getDateWithYear(-2));
		assertEquals(5, discountStrategy.calculateDiscountStrategy(customer));
	}

	@Test
	public void test_NewDiscountStrategy() {
		Customer customer = new Customer("Customer Name", CustomerType.NEW, getDateWithYear(-4));
		assertEquals(0, discountStrategy.calculateDiscountStrategy(customer));
	}
}
