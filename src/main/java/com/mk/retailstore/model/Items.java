package com.mk.retailstore.model;

public class Items {

	int id;
	String itemName;
	ItemCategory itemCategory;
	double amount;

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
