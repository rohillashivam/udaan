package com.udaan.parkinglot.charges.command.impl;

import java.util.Date;

import com.udaan.parkinglot.charges.FlatRate;
import com.udaan.parkinglot.charges.command.AmountCalculatorCommand;

public class FlatChargesCommand implements AmountCalculatorCommand<Double> {

	private FlatRate flatRate;
	
	private Date startTime;
	private Date endTime;
	
	private FlatChargesCommand(FlatRate flatRate, Date startTime, Date endTime) {
		this.flatRate = flatRate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	@Override
	public Double executeCalculation() {
		double amount = findAmountDayAndHours();
		amount += findAmountHours();
		return amount;
	}

	/**
	 * for extension not considered for current scenario
	 * @return
	 */
	private double findAmountHours() {
		// TODO Auto-generated method stub
		return 0;
	}

	private double findAmountDayAndHours() {
		double amount = 0d;
		if(endTime.getDay() - startTime.getDay() > 0) {
			int dayDiff = endTime.getDay() - startTime.getDay();
			amount = flatRate.getPerDay() * dayDiff;
		}
		if(endTime.getHours() > flatRate.getMinHours()) {
			amount += flatRate.getPerDay();
		} else {
			amount += flatRate.getPerHourCost() * endTime.getHours();
		}
		return amount;
	}
	
	public static class FlatChargesCommandBuilder {
		private FlatRate flatRate;
		private Date startTime;
		private Date endTime;

		public FlatChargesCommand build() {
			return new FlatChargesCommand(flatRate, startTime, endTime);
		}
		
		public FlatRate getFlatRate() {
			return flatRate;
		}

		public void setFlatRate(FlatRate flatRate) {
			this.flatRate = flatRate;
		}
		
		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
		
	}

}
