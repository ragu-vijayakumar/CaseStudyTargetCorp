package com.parking.parkingController;

/*Controller interface for Parking which starts the process*/
public interface ParkingController {

	public String checkIn(String license, String type, boolean isTraffic, boolean isRoyalty);

	public void checkOut(String license, String type, boolean isRoyalty);

	public void createParkingLevels(int levels);

}
