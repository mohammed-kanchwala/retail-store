package com.mk.retailstore.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.mk.retailstore.model.Customer;
import com.mk.retailstore.model.CustomerType;
import com.mk.retailstore.service.DiscountStrategy;

@Service
public class DiscountStrategyImpl implements DiscountStrategy {

	@Override
	public int calculateDiscountStrategy(Customer customer) {
		return getDiscountBasedOnCustomerType(customer);
	}

	private int getDiscountBasedOnCustomerType(Customer customer) {
		CustomerType customerType = customer.getCustomerType();
		switch (customerType) {
		case EMPLOYEE:
			return 30;
		case AFFILIATED:
			return 10;
		case REGULAR:
			long years = ChronoUnit.YEARS.between(
					customer.getJoinDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					LocalDateTime.now().toLocalDate());
			if (years >= 2) {
				return 5;
			} else {
				return 0;
			}
		case NEW:
		default:
			return 0;
		}
	}
}
