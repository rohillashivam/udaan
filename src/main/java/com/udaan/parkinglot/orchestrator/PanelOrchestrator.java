package com.udaan.parkinglot.orchestrator;

import java.util.Date;
import java.util.UUID;

import com.udaan.parkinglot.exception.VehicleNotPresentException;
import com.udaan.parkinglot.processor.EntryProcessor;
import com.udaan.parkinglot.processor.ExitProcessor;
import com.udaan.parkinglot.tickets.ParkingTicket;
import com.udaan.parkinglot.tickets.TicketType;

public class PanelOrchestrator {

	private EntryProcessor entryProcessor = new EntryProcessor();
	
	private ExitProcessor exitProcessor = new ExitProcessor();
	
	public ParkingTicket onEntrance(String vehicleNum, UUID parkingLotId, TicketType reserved) throws VehicleNotPresentException {
		return entryProcessor.process(vehicleNum, new Date(), parkingLotId, reserved);
	}
	
	public ParkingTicket onExit(ParkingTicket ticket, Date exitTime) {
		return exitProcessor.process(ticket, exitTime);
		//return exitProcessor.process(ticket, new Date());
	}
	
}
