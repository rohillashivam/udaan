package com.udaan.parkinglot.aggregate.response.impl;

import java.util.Date;

import com.udaan.parkinglot.aggregate.ParkingAggregate;
import com.udaan.parkinglot.aggregate.model.ParkingSpotAgg;
import com.udaan.parkinglot.aggregate.response.ResponseCreator;
import com.udaan.parkinglot.tickets.ParkingTicket;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class ParkingAggregateResponseCreator implements ResponseCreator<ParkingTicket, ParkingAggregate> {

	@Override
	public ParkingAggregate createResponse(ParkingTicket req) {
		return new ParkingAggregate(req.getParkingLot().getAddress().toString(),
				new ParkingSpotAgg(req.getSpot().getFloorNum(), req.getSpot().getSpotType(), req.getSpot().getId()),
				req.getParkingCharges(), findDuration(req.getEntryDate(), req.getExitDate()));

	}

	private String findDuration(Date entryDate, Date exitDate) {
		long timeDiff = exitDate.getTime() - entryDate.getTime();

		long minDiff = (timeDiff / (1000 * 60)) % 60;
		long hoursDiff = (timeDiff / (1000 * 60 * 60)) % 24;
		long daysDiff = (timeDiff / (1000 * 60 * 60 * 24)) % 365;
		
		return daysDiff+" days, "+hoursDiff+" hours, "+minDiff+" mins, ";
	}

}
