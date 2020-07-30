package com.udaan.parkinglot.charges;

public class FlatRate extends ReservationRate {

	private int minHours;
	
	public FlatRate(double perMinCost, double perHourCost, double perDayCost, int minHours) {
		super(perMinCost, perHourCost, perDayCost);
		this.minHours = minHours;
	}

	public int getMinHours() {
		return minHours;
	}

}
