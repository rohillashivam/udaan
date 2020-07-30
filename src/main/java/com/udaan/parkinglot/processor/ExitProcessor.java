package com.udaan.parkinglot.processor;

import java.util.Date;

import com.udaan.parkinglot.ParkingLot;
import com.udaan.parkinglot.ParkingLotChargesType;
import com.udaan.parkinglot.charges.Rate;
import com.udaan.parkinglot.charges.command.adapter.AmountCalculatorCommandAdapter;
import com.udaan.parkinglot.charges.command.impl.FlatChargesCommand;
import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.tickets.ParkingTicket;
import com.udaan.parkinglot.tickets.TicketType;

public class ExitProcessor {
	
	private AmountCalculatorCommandAdapter adapter = new AmountCalculatorCommandAdapter();

	public ParkingTicket process(ParkingTicket ticket, Date date) {
		ticket.setExitDate(date);
		generateParkingCharges(ticket);
		DataRepo.addOrUpdateTicket(ticket);
		return ticket;
	}
	
	private void generateParkingCharges(ParkingTicket ticket) {
		ParkingLot parkingLot = ticket.getParkingLot();
		if(parkingLot.getChargesType().equals(ParkingLotChargesType.FREE))
			ticket.setParkingCharges(0d);
		
		ParkingSpot parkingSpot = ticket.getSpot();
		Rate charges = parkingSpot.getCharges();
		if(ticket.getType().equals(TicketType.RESERVED)) {
			FlatChargesCommand command = (FlatChargesCommand) adapter.adapt(ticket, "flat");
			ticket.setParkingCharges(command.executeCalculation());
		} else if(ticket.getType().equals(TicketType.ADHOC)) {
			
		}
		
	}

}
