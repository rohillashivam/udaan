package com.udaan.parkinglot.spots;

import java.util.UUID;

import com.udaan.parkinglot.charges.Rate;
import com.udaan.parkinglot.vehicles.VehicleType;

public class ParkingSpot {
	
	private UUID id;
	
	private Integer floorNum;

	private SpotType spotType;
	
	private boolean available;
	
	private VehicleType vehicleType;
	
	private Rate charges;
	
	private ParkingSpot(Integer floorNum, SpotType spotType, boolean available, VehicleType vehicleType,
			Rate charges) {
		super();
		this.id = UUID.randomUUID();
		this.floorNum = floorNum;
		this.spotType = spotType;
		this.available = available;
		this.vehicleType = vehicleType;
		this.charges = charges;
	}

	public UUID getId() {
		return id;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public SpotType getSpotType() {
		return spotType;
	}

	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public Rate getCharges() {
		return charges;
	}
	
	public static class ParkingSpotBuilder {
		
		private Integer floorNum;

		private SpotType spotType;
		
		private boolean available;
		
		private VehicleType vehicleType;
		
		private Rate charges;
		
		public ParkingSpot build() {
			return new ParkingSpot(floorNum, spotType, available, vehicleType, charges);
		}

		public Integer getFloorNum() {
			return floorNum;
		}

		public void setFloorNum(Integer floorNum) {
			this.floorNum = floorNum;
		}

		public SpotType getSpotType() {
			return spotType;
		}

		public void setSpotType(SpotType spotType) {
			this.spotType = spotType;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

		public VehicleType getVehicleType() {
			return vehicleType;
		}

		public void setVehicleType(VehicleType vehicleType) {
			this.vehicleType = vehicleType;
		}

		public Rate getCharges() {
			return charges;
		}

		public void setCharges(Rate charges) {
			this.charges = charges;
		}
		
		
	}
	
	
}
