package com.mk.retailstore.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mk.retailstore.model.Customer;
import com.mk.retailstore.model.CustomerType;
import com.mk.retailstore.model.ItemCategory;
import com.mk.retailstore.model.Items;
import com.mk.retailstore.model.ShoppingCart;
import com.mk.retailstore.service.BillCalculator;

public class EmployeeBillCalculationTest extends BillCalculatorTest {

	@Autowired
	private BillCalculator billCalculator;

	@Test
	public void test_calculateNetPayableAmount_Employee_MultipleCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();
		items.add(createItemDetails(ItemCategory.GROCERY, 100.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 350.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 99.99));
		items.add(createItemDetails(ItemCategory.GROCERY, 49.5));
		items.add(createItemDetails(ItemCategory.OTHERS, 52.00));

		shoppingCart.setItems(items);

		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-2));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(505.89, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_GroceryCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();

		items.add(createItemDetails(ItemCategory.GROCERY, 100.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 150.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 55.00));

		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-1));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(290.00, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_NonGroceryCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();

		items.add(createItemDetails(ItemCategory.OTHERS, 100.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 139.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 79.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 49.50));

		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(0));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(247.25, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_EmptyShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();

		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-3));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(0.00, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_LessThan100_MultipleCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();
		items.add(createItemDetails(ItemCategory.GROCERY, 20.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 50.00));
		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-5));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(55.00, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_LessThan100_GroceryCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();
		items.add(createItemDetails(ItemCategory.GROCERY, 20.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 25.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 20.00));
		items.add(createItemDetails(ItemCategory.GROCERY, 25.00));
		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-2));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(90.00, netPayableAmount, 0.00);
	}

	@Test
	public void test_calculateNetPayableAmount_Employee_LessThan100_NonGroceryCombinations() {
		ShoppingCart shoppingCart = new ShoppingCart();
		List<Items> items = new ArrayList<>();
		items.add(createItemDetails(ItemCategory.OTHERS, 20.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 50.00));
		items.add(createItemDetails(ItemCategory.OTHERS, 25.00));
		shoppingCart.setItems(items);
		Customer customer = new Customer("Customer Name", CustomerType.EMPLOYEE, getDateWithYear(-2));
		double netPayableAmount = billCalculator.calculateNetPayableAmount(shoppingCart, customer);
		assertEquals(66.5, netPayableAmount, 0.00);
	}

}
