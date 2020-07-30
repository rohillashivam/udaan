package com.udaan.parkinglot.charges.command.adapter;

import com.udaan.parkinglot.charges.FlatRate;
import com.udaan.parkinglot.charges.command.AmountCalculatorCommand;
import com.udaan.parkinglot.charges.command.impl.FlatChargesCommand;
import com.udaan.parkinglot.charges.command.impl.FlatChargesCommand.FlatChargesCommandBuilder;
import com.udaan.parkinglot.tickets.ParkingTicket;

/**
 * 
 * @author shivam.rohilla
 *
 * @param <PrintTicket>
 */
public class AmountCalculatorCommandAdapter<PrintTicket> {
	
	public AmountCalculatorCommand<Double> adapt(ParkingTicket ticket, String type) {
		if(type == null && type.isEmpty())
			return null;
		
		if(type.contains("flat"))
			return adaptFlatCharges(ticket);
		else if(type.contains("adhoc"))
			return adaptAdhocAmount(ticket);
			
		return null;
	}

	public AmountCalculatorCommand<Double> adaptFlatCharges(ParkingTicket ticket) {
		FlatChargesCommandBuilder flatChargesCommandBuilder = new FlatChargesCommandBuilder();
		flatChargesCommandBuilder.setFlatRate((FlatRate)ticket.getSpot().getCharges());
		flatChargesCommandBuilder.setStartTime(ticket.getEntryDate());
		flatChargesCommandBuilder.setEndTime(ticket.getExitDate());
		FlatChargesCommand command = flatChargesCommandBuilder.build();
		return command;
	}
	
	public AmountCalculatorCommand<Double> adaptAdhocAmount(ParkingTicket ticket) {
		
		return null;
	} 
	
	
}
