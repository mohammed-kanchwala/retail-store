package com.mk.retailstore.model;

import java.util.List;

public class ShoppingCart {

	List<Items> items;
	Customer customer;
	Double amountTotal;
	Double finalPayableAmount;

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public Double getFinalPayableAmount() {
		return finalPayableAmount;
	}

	public void setFinalPayableAmount(Double finalPayableAmount) {
		this.finalPayableAmount = finalPayableAmount;
	}

}
