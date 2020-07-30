package com.udaan.parkinglot.tickets;

import java.util.Date;
import java.util.UUID;

import com.udaan.parkinglot.ParkingLot;
import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.vehicles.Vehicle;

public class ParkingTicket {

	private UUID id;
	
	private Date entryDate;
	
	private Date exitDate;
	
	private TicketType type;
	
	private ParkingSpot spot;
	
	private ParkingLot parkingLot;
	
	private Vehicle vehicle;
	
	private Double parkingCharges;

	public ParkingTicket(Date entryDate, TicketType type, ParkingSpot spot,
			ParkingLot parkingLot, Vehicle vehile) {
		super();
		this.id = UUID.randomUUID();
		this.entryDate = entryDate;
		this.type = type;
		this.spot = spot;
		this.parkingLot = parkingLot;
		this.vehicle = vehile;
	}

	public UUID getId() {
		return id;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public TicketType getType() {
		return type;
	}

	public ParkingSpot getSpot() {
		return spot;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Double getParkingCharges() {
		return parkingCharges;
	}

	public void setParkingCharges(Double parkingCharges) {
		this.parkingCharges = parkingCharges;
	}

	@Override
	public String toString() {
		return "[ entryDate=" + entryDate + ", exitDate=" + exitDate + ", type=" + type
				+ ", spot=" + spot + ", parkingLot=" + parkingLot + ", vehicle=" + vehicle + ", parkingCharges="
				+ parkingCharges + "]";
	}
	

}
