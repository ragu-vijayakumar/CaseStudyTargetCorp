package com.parking.service;

import com.parking.base.Floor;

/** Service for all parking related activities */
public interface ParkingService {

	public int getParkingSpot(Floor floor, String plate) throws IllegalArgumentException;

	public void clearOutgoingVehicle(String licensePlate, String type) throws IllegalArgumentException;
}
