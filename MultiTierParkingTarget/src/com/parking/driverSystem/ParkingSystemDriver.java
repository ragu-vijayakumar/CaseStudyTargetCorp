package com.parking.driverSystem;

import java.util.Scanner;

import com.parking.parkingController.ParkingController;
import com.parking.parkingController.ParkingControllerImpl;

public class ParkingSystemDriver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			String type;
			int levels;
			String licensePlate;
			String availableSpot;

			System.out.println("Number of levels in Multi-Tier Parking");
			levels = sc.nextInt();
			ParkingController parkingController = new ParkingControllerImpl();
			parkingController.createParkingLevels(levels);

			/* Sample inputs for incoming vehicles */
			System.out.println("Enter value incoming vehicle type");
			System.out.println("1.Car	2.Bike");
			type = sc.next();

			System.out.println("Enter License Plate Number");
			licensePlate = sc.next();

			availableSpot = parkingController.checkIn(licensePlate, type, false, false);
			System.out.println(availableSpot);

			System.out.println("\n");
			/* Sample input for outgoing vehicle */
			System.out.println("Enter outgoing vehicle type");
			System.out.println("1.Car	2.Bike");
			type = sc.next();

			System.out.println("Enter License Plate Number");
			licensePlate = sc.next();

			parkingController.checkOut(licensePlate, type, false);

		} catch (Exception e) {
			System.out.println("Sorry we are facing some technical issues");
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
