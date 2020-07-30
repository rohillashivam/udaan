package com.udaan.parkinglot.charges;

public class ReservationRate extends Rate {

	private double perDay;

	public ReservationRate(double perMinCost, double perHourCost, double perDay) {
		super(perMinCost, perHourCost);
		this.perDay = perDay;
	}

	public double getPerDay() {
		return perDay;
	}

}
