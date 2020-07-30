package com.udaan.parkinglot.service.address;

import com.udaan.parkinglot.Address;
import com.udaan.parkinglot.service.AddressService;

public class AddressProxy {

	private AddressService addressSvc = new AddressService();
	
	public Address buildAddress(String pincode, String country, String state, String city, String street) {
		return addressSvc.buildAddress(pincode, country, state, city, street);
	}
}
