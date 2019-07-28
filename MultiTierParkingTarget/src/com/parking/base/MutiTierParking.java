package com.parking.base;

public class MutiTierParking {

	private Floor[] floorsArray;

	public MutiTierParking(int levels) {
		floorsArray = new Floor[levels];
		for (int i = 0; i < levels; i++) {
			Floor floor = new Floor(i + 1);
			floorsArray[i] = floor;
		}
	}

	public static void checkOut(String license, String type) {

	}

	public Floor[] getFloorsArray() {
		return floorsArray;
	}

	public void setFloorsArray(Floor[] floorsArray) {
		this.floorsArray = floorsArray;
	}

}
