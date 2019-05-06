package com.mk.retailstore.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.mk.retailstore.RetailStoreApplicationTests;
import com.mk.retailstore.model.ItemCategory;
import com.mk.retailstore.model.Items;

public class BillCalculatorTest extends RetailStoreApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static Items createItemDetails(ItemCategory itemCategory, double amount) {
		Items item = new Items();
		item.setItemCategory(itemCategory);
		item.setAmount(amount);
		return item;
	}

	public static Date getDateWithYear(int year) {
		Date joinDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year);
		joinDate = cal.getTime();
		return joinDate;
	}

}
