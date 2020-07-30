package com.udaan.parkinglot.orchestrator;

import java.util.Optional;

import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.vehicles.Vehicle;

public class VehicleService {
	
	public void addVehicle(Vehicle vehicle) {
		DataRepo.addVehicle(vehicle);
	}
	
	public Optional<Vehicle> getVehicle(String vehicleNum) {
		return Optional.ofNullable(DataRepo.getVehicle(vehicleNum));
	}

}
