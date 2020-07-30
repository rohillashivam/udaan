package com.udaan.parkinglot.service;

import com.udaan.parkinglot.Address;

public class AddressService {

	public Address buildAddress(String pincode, String country, String state, String city, String street) {
		return new Address(pincode, country, state, city, street);
	}
}
