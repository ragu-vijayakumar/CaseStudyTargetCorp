package com.parking.util;

import java.util.ArrayList;

import com.parking.base.Floor;
import com.parking.base.ParkingSpot;
import com.parking.base.VehicleTypes;

public class ParkingUtil {

	/** Function to convert parking based on vehicle type */
	public static boolean convertAvailableParkingBasedOnType(Floor floor, String parkingNeededType) {
		boolean addedParking = Boolean.FALSE;
		for (ParkingSpot parkingSpot : floor.getParkingSpots()) {
			if (!parkingSpot.getVehicleType().equalsIgnoreCase(parkingNeededType) && parkingSpot.isAvailable()) {
				parkingSpot.setVehicleType(parkingNeededType.toUpperCase());
				addedParking = Boolean.TRUE;
			}
		}
		return addedParking;
	}

	private static ArrayList<ParkingSpot> getCarParkingSpotsForRoyalty(Floor floor) {
		ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
		for (ParkingSpot parkingSpot : floor.getParkingSpots()) {
			if (parkingSpot.getVehicleType().equalsIgnoreCase(VehicleTypes.CAR.toString())
					&& parkingSpot.isAvailable()) {
				parkingSpots.add(parkingSpot);
				if (parkingSpots.size() == 3) {
					break;
				}
			}
		}
		return parkingSpots;
	}

	public static int getParkingSpotForRoyalty(Floor floor, String license) throws IllegalArgumentException {
		ArrayList<ParkingSpot> parkingSpots = ParkingUtil.getCarParkingSpotsForRoyalty(floor);
		ParkingSpot parkingSpot;
		if (parkingSpots.size() >= 3) {
			for (ParkingSpot p : parkingSpots) {
				p.setAvailable(false);
				p.setAssignedToRoyalty(true);
			}
			parkingSpot = parkingSpots.get(parkingSpots.size() - 2);
			int totalVehicles = parkingSpot.getTotalVehicles();
			parkingSpot.setTotalVehicles(++totalVehicles);
			Floor.royaltyVehicleMap.put(license, parkingSpots);
			return parkingSpot.getSpotNumber();
		} else {
			throw new IllegalArgumentException("No Parking Spot available");
		}
	}

	public static void clearParkingSlotsAssignedToRoyalty(String license) throws IllegalArgumentException {
		ArrayList<ParkingSpot> spots = Floor.royaltyVehicleMap.get(license);

		if (null == spots) {
			throw new IllegalArgumentException("No vehicle present with given license number");
		} else {
			for (ParkingSpot p : spots) {
				p.setAvailable(true);
				p.setTotalVehicles(0);
				p.setAssignedToRoyalty(false);
			}
			Floor.royaltyVehicleMap.remove(license);
		}
	}
}
