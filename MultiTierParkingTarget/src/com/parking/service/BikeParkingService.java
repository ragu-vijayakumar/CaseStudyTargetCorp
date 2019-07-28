package com.parking.service;

import com.parking.base.Floor;
import com.parking.base.ParkingSpot;
import com.parking.base.Vehicle;
import com.parking.base.VehicleTypes;

public class BikeParkingService implements ParkingService{

	@Override
	public int getParkingSpot(Floor floor, String plate) throws IllegalArgumentException {
		ParkingSpot parkingSpot = floor.getNextParkingSpot(VehicleTypes.BIKE.toString());
		if (parkingSpot == null) {
			throw new IllegalArgumentException("No Parking Spot Aavailable");
		} else {
			int totalVehicles = parkingSpot.getTotalVehicles();
			parkingSpot.setTotalVehicles(++totalVehicles);
			parkingSpot.getVehicles().add(new Vehicle(VehicleTypes.CAR.toString(),plate));

			if (totalVehicles == 5)
				parkingSpot.setAvailable(Boolean.FALSE);
			Floor.vehicleMap.put(plate.toUpperCase(), parkingSpot);
			return parkingSpot.getSpotNumber();
		}
	}

	@Override
	public void clearOutgoingVehicle(String licensePlate, String type) throws IllegalArgumentException{
		ParkingSpot parkingSpot=Floor.vehicleMap.get(licensePlate.toUpperCase());
		int totalVehiles;
		
		if(null==parkingSpot){
			throw new IllegalArgumentException("No vehicle found with given license plate");
		}else{
			totalVehiles=parkingSpot.getTotalVehicles();
			--totalVehiles;
			
			if(totalVehiles==0)
				parkingSpot.setAvailable(Boolean.TRUE);;
				
			parkingSpot.setTotalVehicles(totalVehiles);
			Floor.vehicleMap.remove(licensePlate.toUpperCase());
			parkingSpot.removeGivenVehicle(licensePlate);
		}
		
	}
	

}
