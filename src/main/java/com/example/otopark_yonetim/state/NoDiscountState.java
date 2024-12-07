package com.example.otopark_yonetim.state;

public class NoDiscountState implements DiscountState {
	@Override
	public int handleDiscount() {
		return 0;
	}
}