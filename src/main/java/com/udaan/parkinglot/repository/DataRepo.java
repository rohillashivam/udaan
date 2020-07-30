package com.udaan.parkinglot.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.udaan.parkinglot.ParkingLot;
import com.udaan.parkinglot.tickets.ParkingTicket;
import com.udaan.parkinglot.vehicles.Vehicle;

public class DataRepo {

	private static  Map<UUID, ParkingLot> parkingLotMap = new ConcurrentHashMap<UUID, ParkingLot>();
	
	private static Map<String, Vehicle> vehicleMap = new ConcurrentHashMap<>();
	
	private static Map<String, LinkedList<ParkingTicket>> vehicleAggregateMap = new HashMap<>();
	
	public static void addParkingLot(ParkingLot parkingLot) {
		parkingLotMap.put(parkingLot.getId(), parkingLot);
	}
	
	public static void addVehicle(Vehicle vehicle) {
		vehicleMap.put(vehicle.getNumber(), vehicle);
	}
	
	public static Vehicle getVehicle(String vehicleNum) {
		return vehicleMap.get(vehicleNum);
	}
	
	public static ParkingLot getParkingLot(UUID parkingLotId) {
		return parkingLotMap.get(parkingLotId);
	}

	public static void addOrUpdateTicket(ParkingTicket ticket) {
		synchronized(ticket.getVehicle().getNumber()) {
			LinkedList<ParkingTicket> parkingTicketList = new LinkedList<>();
			if(!vehicleAggregateMap.containsKey(ticket.getVehicle().getNumber())) {
				vehicleAggregateMap.put(ticket.getVehicle().getNumber(), parkingTicketList);
			}

			parkingTicketList = vehicleAggregateMap.get(ticket.getVehicle().getNumber());
			if(!parkingTicketList.isEmpty() && parkingTicketList.getLast().getId().equals(ticket.getId())) {
				parkingTicketList.removeLast();
			}
			parkingTicketList.addLast(ticket);
		}
	}
	
	public static List<ParkingTicket> getHistory(String vehicleNum) {
		if(!vehicleAggregateMap.containsKey(vehicleNum))
			return new ArrayList<>();
		
		return vehicleAggregateMap.get(vehicleNum);
		
	}
}
