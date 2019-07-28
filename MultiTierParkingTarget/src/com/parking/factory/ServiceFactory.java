package com.parking.factory;

import com.parking.base.VehicleTypes;
import com.parking.service.BikeParkingService;
import com.parking.service.CarParkingService;
import com.parking.service.ParkingService;

/**Factory class to produce required service object*/
public class ServiceFactory {

	public static ParkingService getService(String type) {
		if (type.equalsIgnoreCase(VehicleTypes.BIKE.toString())) {
			return new BikeParkingService();
		} else if (type.equalsIgnoreCase(VehicleTypes.CAR.toString())) {
			return new CarParkingService();
		} else {
			return null;
		}
	}

}
