package com.parking.base;

import java.util.ArrayList;

public class ParkingSpot {

	ParkingSpot(int id, String vehicleType, int floor) {
		this.spotNumber = id;
		this.vehicleType = vehicleType;
		this.floorLevel = floor;
	}

	private int totalVehicles = 0;
	private String vehicleType;
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	private int spotNumber;
	private int floorLevel;
	private boolean isAvailable = Boolean.TRUE;
	boolean isNearExit = Boolean.FALSE;
	boolean isAssignedToRoyalty=Boolean.FALSE;

	public int getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(int spotNumber) {
		this.spotNumber = spotNumber;
	}

	public int getTotalVehicles() {
		return totalVehicles;
	}

	public void setTotalVehicles(int totalVehicles) {
		this.totalVehicles = totalVehicles;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getFloorLevel() {
		return floorLevel;
	}

	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean isNearExit() {
		return isNearExit;
	}

	public void setNearExit(boolean isNearExit) {
		this.isNearExit = isNearExit;
	}

	public boolean isAssignedToRoyalty() {
		return isAssignedToRoyalty;
	}

	public void setAssignedToRoyalty(boolean isAssignedToRoyalty) {
		this.isAssignedToRoyalty = isAssignedToRoyalty;
	}

	public void removeGivenVehicle(String licensePlate) {
		Vehicle removeVehicle = null;
		for (Vehicle v : this.vehicles) {
			if (v.getLicensePlate().equalsIgnoreCase(licensePlate)) {
				removeVehicle = v;
				break;
			}
		}
		if (null != removeVehicle)
			this.vehicles.remove(removeVehicle);
	}

}
