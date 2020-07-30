package com.udaan.parkinglot.processor;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.udaan.parkinglot.ParkingLot;
import com.udaan.parkinglot.exception.VehicleNotPresentException;
import com.udaan.parkinglot.orchestrator.VehicleService;
import com.udaan.parkinglot.pool.ParkingLotPool;
import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.tickets.ParkingTicket;
import com.udaan.parkinglot.tickets.TicketType;
import com.udaan.parkinglot.vehicles.Vehicle;

public class EntryProcessor {

	private VehicleService vehicleSvc = new VehicleService();
	
	public ParkingTicket process(String vehicleNum, Date date, UUID parkingLotId, TicketType ticketType) throws VehicleNotPresentException {
		ParkingLot parkingLot = DataRepo.getParkingLot(parkingLotId);
		Optional<Vehicle> vehicleOpt = vehicleSvc.getVehicle(vehicleNum);
		if(!vehicleOpt.isPresent()) {
			throw new VehicleNotPresentException("Vehicle not present");
		}
		Optional<ParkingSpot> parkingLotOpt = ParkingLotPool.getAvailableParkingSpot(vehicleOpt.get().getVehicleType(), 
				parkingLotId);
		if(!parkingLotOpt.isPresent()) {
			// throw exception
		}
		ParkingTicket ticket = new ParkingTicket(date, ticketType != null ? ticketType : TicketType.ADHOC, parkingLotOpt.get(), parkingLot, vehicleOpt.get());
		new Thread(() ->  {
			DataRepo.addOrUpdateTicket(ticket);
		}).start();
		return ticket;
	}
}
