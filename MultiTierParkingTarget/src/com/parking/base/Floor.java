package com.parking.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Floor {

	Floor(int lv) {
		level = lv;
	}

	private int level;
	ArrayList<ParkingSpot> parkingSpots;
	public static Map<String, ParkingSpot> vehicleMap = new HashMap<String, ParkingSpot>();
	public static Map<String, ArrayList<ParkingSpot>> royaltyVehicleMap = new HashMap<String, ArrayList<ParkingSpot>>();
	// Assuming the right side of the floor is the exit
	public final ArrayList<Integer> SPOTS_CLOSER_TO_EXIT = new ArrayList<Integer>(Arrays.asList(5, 10, 15, 20));

	public ParkingSpot getNextParkingSpot(String vehicleType) {
		for (ParkingSpot parkingSpot : getParkingSpots()) {
			if (parkingSpot.getVehicleType().equalsIgnoreCase(vehicleType) && parkingSpot.isAvailable()) {
				return parkingSpot;
			}
		}
		return null;
	}

	public boolean convertAvailableParking(String from, String to) {
		boolean converted = false;
		for (ParkingSpot parkingSpot : getParkingSpots()) {
			if (parkingSpot.getVehicleType().equalsIgnoreCase(from) && parkingSpot.isAvailable()) {
				parkingSpot.setVehicleType(to);
				converted = true;
			}
		}
		return converted;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<ParkingSpot> getParkingSpots() {
		return this.parkingSpots;
	}

	public void setParkingSpots(ArrayList<ParkingSpot> parkingSpots) {
		this.parkingSpots = parkingSpots;
	}

	public void populateParkingSpots() {
		ArrayList<ParkingSpot> parkingSpotList;
		ParkingSpot parkingSpot;
		int i;
		if (this.parkingSpots == null) {
			parkingSpotList = new ArrayList<ParkingSpot>();
			this.parkingSpots = new ArrayList<ParkingSpot>();
			;
			for (i = 1; i <= 10; i++) {
				parkingSpot = new ParkingSpot(i, VehicleTypes.BIKE.toString(), getLevel());
				if (this.SPOTS_CLOSER_TO_EXIT.contains(i))
					parkingSpot.setNearExit(Boolean.TRUE);

				parkingSpotList.add(parkingSpot);
			}
			for (i = 11; i <= 20; i++) {
				parkingSpot = new ParkingSpot(i, VehicleTypes.CAR.toString(), getLevel());
				if (this.SPOTS_CLOSER_TO_EXIT.contains(i))
					parkingSpot.setNearExit(Boolean.TRUE);

				parkingSpotList.add(parkingSpot);
			}
			this.parkingSpots.addAll(parkingSpotList);
		}
	}

}
