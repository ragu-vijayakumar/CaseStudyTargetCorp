package com.parking.parkingController;

import com.parking.base.Floor;
import com.parking.base.MutiTierParking;
import com.parking.base.VehicleTypes;
import com.parking.factory.ServiceFactory;
import com.parking.service.ParkingService;
import com.parking.util.ParkingUtil;

public class ParkingControllerImpl implements ParkingController {
	private MutiTierParking multiTierParker;
	private ParkingService parkingService;

	public void createParkingLevels(int levels) {
		if (levels > 0) {
			if (getMultiTierParker() == null) {
				setMultiTierParker(new MutiTierParking(levels));
			}
		} else {
			System.out.println("Please enter valid positive Value");
		}
	}

	public String checkIn(String license, String type, boolean isTraffic, boolean isRoyalty) {
		int spotNumber;

		for (Floor f : getMultiTierParker().getFloorsArray()) {
			f.populateParkingSpots();
			if (type.equalsIgnoreCase(VehicleTypes.CAR.toString())) {
				try {
					this.parkingService = ServiceFactory.getService(VehicleTypes.CAR.toString());
					if (isRoyalty) {
						spotNumber = ParkingUtil.getParkingSpotForRoyalty(f, license);
					} else {
						spotNumber = this.parkingService.getParkingSpot(f, license);
						return "Parking Spot: Floor-" + f.getLevel() + " ::Slot-" + spotNumber;
					}
				} catch (Exception e) {
					// System.out.println("No Parking slot");
					continue;
				}

			} else if (type.equalsIgnoreCase(VehicleTypes.BIKE.toString())) {
				try {
					/** IN Case of Traffic */

					if (isTraffic)
						ParkingUtil.convertAvailableParkingBasedOnType(f, VehicleTypes.BIKE.toString());

					this.parkingService = ServiceFactory.getService(VehicleTypes.BIKE.toString());
					spotNumber = parkingService.getParkingSpot(f, license);
					return "Parking Spot: Floor-" + f.getLevel() + " ::Slot-" + spotNumber;
				} catch (Exception e) {
					// System.out.println("No Parking slot");
					continue;
				}
			} else {
				return "Please enter a valid vehicle type";
			}
		}
		return "No Parking slot";
	}

	/* Clear outgoing vehicle based on Royalty or not */
	@Override
	public void checkOut(String license, String type, boolean isRoyalty) {
		try {
			if (type.equalsIgnoreCase(VehicleTypes.CAR.toString())) {
				
				if (isRoyalty) {
					ParkingUtil.clearParkingSlotsAssignedToRoyalty(license);
				} else {
					this.parkingService = ServiceFactory.getService(VehicleTypes.CAR.toString());
					this.parkingService.clearOutgoingVehicle(license, type);
				}

			} else if (type.equalsIgnoreCase(VehicleTypes.BIKE.toString())) {
				this.parkingService = ServiceFactory.getService(VehicleTypes.BIKE.toString());
				this.parkingService.clearOutgoingVehicle(license, type);

			} else {
				System.out.println("Please enter a valid vehicle type");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public MutiTierParking getMultiTierParker() {
		return multiTierParker;
	}

	public void setMultiTierParker(MutiTierParking multiTierParker) {
		this.multiTierParker = multiTierParker;
	}

	public ParkingService getParkingService(String type) {
		return this.parkingService;
	}

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

}
