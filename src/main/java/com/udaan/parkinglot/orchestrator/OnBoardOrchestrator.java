package com.udaan.parkinglot.orchestrator;

import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.vehicles.Vehicle;
import com.udaan.parkinglot.vehicles.VehicleType;

public class OnBoardOrchestrator {

	public Vehicle onboardVehicle(String num, VehicleType type) {
		Vehicle vehicle = new Vehicle(type, num);
		DataRepo.addVehicle(vehicle);
		return vehicle;
	}
}
