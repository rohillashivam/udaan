package com.udaan.parkinglot.vehicles;

import java.util.UUID;

public class Vehicle {

	private UUID id;
	
	private VehicleType vehicleType;
	
	private String number;

	public Vehicle(VehicleType vehicleType, String number) {
		super();
		this.id = UUID.randomUUID();
		this.vehicleType = vehicleType;
		this.number = number;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
