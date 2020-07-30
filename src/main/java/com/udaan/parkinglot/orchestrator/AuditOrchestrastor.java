package com.udaan.parkinglot.orchestrator;

import java.util.ArrayList;
import java.util.List;

import com.udaan.parkinglot.aggregate.ParkingAggregate;
import com.udaan.parkinglot.aggregate.response.ResponseCreator;
import com.udaan.parkinglot.aggregate.response.impl.ParkingAggregateResponseCreator;
import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.tickets.ParkingTicket;

public class AuditOrchestrastor {

	private ResponseCreator<ParkingTicket, ParkingAggregate> responseCreator = new ParkingAggregateResponseCreator();
	
	public List<ParkingAggregate> getHistoryOfVehicle(String vehicleNum) {
		List<ParkingTicket> parkingTicketList = DataRepo.getHistory(vehicleNum);
		List<ParkingAggregate> paList = new ArrayList<ParkingAggregate>(parkingTicketList.size());
		for (ParkingTicket pt : parkingTicketList) {
			paList.add(responseCreator.createResponse(pt));
		}
		return paList;
	}
}
