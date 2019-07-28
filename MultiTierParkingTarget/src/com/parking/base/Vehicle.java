package com.parking.base;

public class Vehicle {
	private String licensePlate;
	private String type;

	/*
	 * Additional Properties for the vehicles If we have requirements for
	 * different vehicles we can create new concreate classes
	 */
	private double height;

	public Vehicle(String type, String licensePlate) {
		this.type = type;
		this.licensePlate = licensePlate;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
