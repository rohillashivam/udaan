package com.udaan.parkinglot.charges;

public class Rate {

	private double perMinCost;
	private double perHourCost;
	
	public Rate(double perMinCost, double perHourCost) {
		super();
		this.perMinCost = perMinCost;
		this.perHourCost = perHourCost;
	}

	public double getPerMinCost() {
		return perMinCost;
	}

	public void setPerMinCost(double perMinCost) {
		this.perMinCost = perMinCost;
	}

	public double getPerHourCost() {
		return perHourCost;
	}

	public void setPerHourCost(double perHourCost) {
		this.perHourCost = perHourCost;
	}
	
	
}
