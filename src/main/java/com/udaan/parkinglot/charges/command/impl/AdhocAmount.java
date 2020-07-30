package com.udaan.parkinglot.charges.command.impl;

import com.udaan.parkinglot.charges.command.AmountCalculatorCommand;

/**
 * not handled for now can be handled same way as Reserved parking/Flat case
 * @author shivam.rohilla
 *
 */
public class AdhocAmount implements AmountCalculatorCommand<Double> {

	@Override
	public Double executeCalculation() {
		return null;
	}

}
