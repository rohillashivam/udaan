package com.udaan.parkinglot.charges.command;

public interface AmountCalculatorCommand<Response> {

	public Response executeCalculation();
}
