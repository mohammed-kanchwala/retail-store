package com.mk.retailstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.retailstore.model.Customer;
import com.mk.retailstore.model.ItemCategory;
import com.mk.retailstore.model.Items;
import com.mk.retailstore.model.ShoppingCart;
import com.mk.retailstore.service.BillCalculator;
import com.mk.retailstore.service.DiscountStrategy;

@Service
public class BillCalculatorImpl implements BillCalculator {

	@Autowired
	DiscountStrategy discountStrategy;

	@Override
	public double calculateNetPayableAmount(ShoppingCart shoppingCart, Customer customer) {

		List<Items> items = shoppingCart.getItems();
		double nonGroceryAmount = items.stream().filter(item -> item.getItemCategory() != ItemCategory.GROCERY)
				.mapToDouble(Items::getAmount).reduce(0, (a, b) -> a + b);
		double groceryAmount = items.stream().filter(item -> item.getItemCategory() == ItemCategory.GROCERY)
				.mapToDouble(Items::getAmount).reduce(0, (a, b) -> a + b);

		Double totalAmountAfterDiscount = calculateTheAmountAfterApplyingDiscount(nonGroceryAmount,
				customer);

		shoppingCart.setAmountTotal(totalAmountAfterDiscount + groceryAmount);

		shoppingCart.setFinalPayableAmount(applyAdditionalDiscount(shoppingCart.getAmountTotal()));
		return shoppingCart.getFinalPayableAmount();
	}

	private Double calculateTheAmountAfterApplyingDiscount(Double totalAmount, Customer customer) {

		int discount = discountStrategy.calculateDiscountStrategy(customer);

		double discountApplied = (double) discount / 100;

		totalAmount = totalAmount - (totalAmount * discountApplied);

		return totalAmount;
	}

	private double applyAdditionalDiscount(Double totalAmountAfterDiscount) {

		int noOfMultiples = (int) (totalAmountAfterDiscount / 100);
		double discontAmount = (noOfMultiples * 5);
		return totalAmountAfterDiscount - discontAmount;

	}

}
