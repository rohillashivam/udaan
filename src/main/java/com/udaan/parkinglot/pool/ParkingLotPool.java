package com.udaan.parkinglot.pool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.vehicles.VehicleType;

public class ParkingLotPool {

	private static Map<UUID, Map<VehicleType, Queue<ParkingSpot>>> availableSpot = new HashMap<>();
	private static Map<UUID, Map<VehicleType, Queue<ParkingSpot>>> usedSpot =  new HashMap<>();
	
	public static void poolBuilder(List<ParkingSpot> parkingSpotList, UUID parkingLotId) {
		Map<VehicleType, Queue<ParkingSpot>> vehicleMap = new HashMap<>();
		for (ParkingSpot parkingSpot : parkingSpotList) {
			VehicleType vehicleType = parkingSpot.getVehicleType();
			switch (vehicleType) {
			case TWO_WHEELER:
				updateParkingSpot(vehicleMap, parkingSpot, VehicleType.TWO_WHEELER);
				break;
			case SUV:
				updateParkingSpot(vehicleMap, parkingSpot, VehicleType.SUV);
				break;
			case HATCH_BACK:
				updateParkingSpot(vehicleMap, parkingSpot, VehicleType.HATCH_BACK);
				break;
			default:
				break;
			}
			
		}
		availableSpot.put(parkingLotId, vehicleMap);
		Map<VehicleType, Queue<ParkingSpot>> vehicleMapUsed = new HashMap<>();
		for(VehicleType vehicleTypeObj : VehicleType.values()) {
			Queue<ParkingSpot> parkingSpotQueueUsed = new LinkedList<>();
			vehicleMapUsed.put(vehicleTypeObj, parkingSpotQueueUsed);
		}
		usedSpot.put(parkingLotId,vehicleMapUsed);
	}

	private static void updateParkingSpot(Map<VehicleType, Queue<ParkingSpot>> vehicleMap, ParkingSpot parkingSpot, VehicleType vehicleType) {
		parkingSpot.setAvailable(true);
		if(!vehicleMap.containsKey(vehicleType)) {
			Queue<ParkingSpot> parkingSpotQueueUsed = new LinkedList<>();
			vehicleMap.put(vehicleType, parkingSpotQueueUsed);
		}
		vehicleMap.get(vehicleType).add(parkingSpot);
	}
	
	public static Optional<ParkingSpot> getAvailableParkingSpot(VehicleType vehicleType, UUID parkingLotId) {
		// validation later
		Map<VehicleType, Queue<ParkingSpot>> availableMap = availableSpot.get(parkingLotId);
		if(availableMap.get(vehicleType).isEmpty())
			return Optional.empty();
		synchronized(availableMap.get(vehicleType)) {
			ParkingSpot spot = availableMap.get(vehicleType).remove();
			synchronized (usedSpot.get(parkingLotId).get(vehicleType)) {
				usedSpot.get(parkingLotId).get(vehicleType).add(spot);
			}
			return Optional.ofNullable(spot);
		}
	}
}


// entity -- done
// behaviour -- spot -- pool
// 1 parking lot
// entry and exit -- need to be done
// vehicle registrartion -- entry
//