package com.udaan.parkinglot.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.udaan.parkinglot.Address;
import com.udaan.parkinglot.ParkingLot;
import com.udaan.parkinglot.ParkingLotChargesType;
import com.udaan.parkinglot.ParkingLotType;
import com.udaan.parkinglot.aggregate.ParkingAggregate;
import com.udaan.parkinglot.charges.FlatRate;
import com.udaan.parkinglot.charges.Rate;
import com.udaan.parkinglot.exception.VehicleNotPresentException;
import com.udaan.parkinglot.orchestrator.AuditOrchestrastor;
import com.udaan.parkinglot.orchestrator.OnBoardOrchestrator;
import com.udaan.parkinglot.orchestrator.PanelOrchestrator;
import com.udaan.parkinglot.pool.ParkingLotPool;
import com.udaan.parkinglot.repository.DataRepo;
import com.udaan.parkinglot.spots.ParkingSpot;
import com.udaan.parkinglot.spots.ParkingSpot.ParkingSpotBuilder;
import com.udaan.parkinglot.spots.SpotType;
import com.udaan.parkinglot.state.STATE;
import com.udaan.parkinglot.tickets.ParkingTicket;
import com.udaan.parkinglot.tickets.TicketType;
import com.udaan.parkinglot.vehicles.VehicleType;

public class Test {

	public static void main(String[] args) {
		AuditOrchestrastor aO = new AuditOrchestrastor();
		// case for initialization and reserved parking
		List<Rate> rateList = buildRateList(1d, 12d, 20d);
		int twoWheelerParkingSpots = 10, fourWheelerSUVParkingSpot = 7, fourWheelerHBParkingSpot = 10;
		List<ParkingSpot> psList = new ArrayList<>();
		buildTwoWheelerList(rateList, twoWheelerParkingSpots, psList, rateList.stream().findFirst().get());
		buildSUV(rateList, fourWheelerSUVParkingSpot, psList, rateList.stream().findFirst().get());
		buildHB(rateList, fourWheelerHBParkingSpot, psList, rateList.stream().findFirst().get());
		ParkingLot parkingLot = new ParkingLot("test",
				new Address("560103", "INDIA", "Karnataka", "Bengaluru", "Kadubesanhalli"), psList,
				ParkingLotType.COMMERCIAL, ParkingLotChargesType.PAID, STATE.ACTIVE, rateList);
		DataRepo.addParkingLot(parkingLot);
		ParkingLotPool.poolBuilder(parkingLot.getParkingSpots(), parkingLot.getId());
		
		PanelOrchestrator panelOrch = new PanelOrchestrator();
		OnBoardOrchestrator onboardOrchestrator = new OnBoardOrchestrator();
		boolean vehicleFound = false;
		String vehicleNum = "KA104857";
		ParkingTicket ticket = buildTicket(parkingLot, panelOrch, onboardOrchestrator, vehicleFound, vehicleNum);
		Calendar date = Calendar.getInstance();
		long time= date.getTimeInMillis();
		Date afterAddingTime=new Date(time + (10 * 28800000));
		panelOrch.onExit(ticket, afterAddingTime);
		
		//for same vehicle with different time
		ticket = buildTicket(parkingLot, panelOrch, onboardOrchestrator, vehicleFound, vehicleNum);
		date = Calendar.getInstance();
		time= date.getTimeInMillis();
		afterAddingTime=new Date(time + (10 * 14400000));
		panelOrch.onExit(ticket, afterAddingTime);
		
		// printing all the histroy
		final List<ParkingAggregate> ticketList = aO.getHistoryOfVehicle(vehicleNum);
		ticketList.forEach(ticketObj -> System.out.println(ticketObj.toString()));
	}

	private static ParkingTicket buildTicket(ParkingLot parkingLot, PanelOrchestrator panelOrch,
			OnBoardOrchestrator onboardOrchestrator, boolean vehicleFound, String vehicleNum) {
		ParkingTicket ticket = null;
		int rerty=3, count=0;
		while(!vehicleFound && count<rerty) {
			try {
				ticket = panelOrch.onEntrance(vehicleNum, parkingLot.getId(), TicketType.RESERVED);
				break;
			} catch (VehicleNotPresentException e) {
				onboardOrchestrator.onboardVehicle(vehicleNum, VehicleType.TWO_WHEELER);
				System.out.println(e.getMessage());
			}
		}
		return ticket;
	}

	private static void buildHB(List<Rate> rateList, int fourWheelerHBParkingSpot, List<ParkingSpot> psList, Rate rate) {
		for(int j=0; j<fourWheelerHBParkingSpot; j++) {
			ParkingSpotBuilder psb = new ParkingSpotBuilder();
			psb.setAvailable(true);
			psb.setCharges(rate);
			psb.setFloorNum(1);
			psb.setSpotType(SpotType.FOUR_WHEELER);
			psb.setVehicleType(VehicleType.HATCH_BACK);
			psList.add(psb.build());
		}
	}

	private static void buildSUV(List<Rate> rateList, int fourWheelerSUVParkingSpot, List<ParkingSpot> psList, Rate rate) {
		for(int j=0; j<fourWheelerSUVParkingSpot; j++) {
			ParkingSpotBuilder psb = new ParkingSpotBuilder();
			psb.setAvailable(true);
			psb.setCharges(rate);
			psb.setFloorNum(1);
			psb.setSpotType(SpotType.FOUR_WHEELER);
			psb.setVehicleType(VehicleType.SUV);
			psList.add(psb.build());
		}
	}

	private static void buildTwoWheelerList(List<Rate> rateList, int twoWheelerParkingSpots, List<ParkingSpot> psList, Rate rate) {
		for(int i=0; i<twoWheelerParkingSpots; i++) {
			ParkingSpotBuilder psb = new ParkingSpotBuilder();
			psb.setAvailable(true);
			psb.setCharges(rate);
			psb.setFloorNum(1);
			psb.setSpotType(SpotType.TWO_WHEELER);
			psb.setVehicleType(VehicleType.TWO_WHEELER);
			psList.add(psb.build());
		}
	}

	private static List<Rate> buildRateList(double perMin, double perHour, double perDay) {
		Rate rate = new FlatRate(1d, 12d, 20d, 8);
		List<Rate> rateList = new ArrayList<>();
		rateList.add(rate);
		return rateList;
	}
}
