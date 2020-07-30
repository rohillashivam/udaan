package com.udaan.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.udaan.parkinglot.charges.Rate;
import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.state.STATE;

public final class ParkingLot {

	private final UUID id;
	
	private final String name;
	
	private final Address address;
	
	private final List<ParkingSpot> parkingSpots;
	
	private final ParkingLotType type;
	
	private final ParkingLotChargesType chargesType;
	
	private STATE state;
	
	private List<Rate> charges;

	public ParkingLot(String name, Address address, List<ParkingSpot> parkingSpots, ParkingLotType type,
			ParkingLotChargesType chargesType, STATE state, List<Rate> charges) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.address = address;
		this.parkingSpots = parkingSpots;
		this.type = type;
		this.chargesType = chargesType;
		this.state = state;
		this.charges = charges;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public List<ParkingSpot> getParkingSpots() {
		return new ArrayList<ParkingSpot>(parkingSpots);
	}

	public ParkingLotType getType() {
		return type;
	}

	public ParkingLotChargesType getChargesType() {
		return chargesType;
	}

	public STATE getState() {
		return state;
	}
	
	public void updateState(STATE state) {
		this.state = state;
	}
	
}
