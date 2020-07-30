package com.udaan.parkinglot.aggregate;

import com.udaan.parkinglot.aggregate.model.ParkingSpotAgg;

public class ParkingAggregate {

	private String parkingLotAddress;
	
	private ParkingSpotAgg parkingSlot;
	
	private Double amountPaid;
	
	private String duration;

	public ParkingAggregate(String parkingLotAddress, ParkingSpotAgg parkingSlot, Double amountPaid, String duration) {
		super();
		this.parkingLotAddress = parkingLotAddress;
		this.parkingSlot = parkingSlot;
		this.amountPaid = amountPaid;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "ParkingAggregate [parkingLotAddress=" + parkingLotAddress + ", parkingSlot=" + parkingSlot
				+ ", amountPaid=" + amountPaid + ", duration=" + duration + "]";
	}
	
	
}
