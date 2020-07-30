package com.udaan.parkinglot.aggregate.model;

import java.util.UUID;

import com.udaan.parkinglot.spots.SpotType;

public class ParkingSpotAgg {

	private int floorNum;
	private SpotType spotType;
	private UUID parkingSpotId;
	
	public ParkingSpotAgg(int floorNum, SpotType spotType, UUID parkingSpotId) {
		super();
		this.floorNum = floorNum;
		this.spotType = spotType;
		this.parkingSpotId = parkingSpotId;
	}

	@Override
	public String toString() {
		return "ParkingSpotAgg [floorNum=" + floorNum + ", spotType=" + spotType + ", parkingSpotId=" + parkingSpotId
				+ "]";
	}
	
	
}
