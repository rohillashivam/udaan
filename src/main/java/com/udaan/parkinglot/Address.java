package com.udaan.parkinglot;

import java.util.UUID;

public class Address {

	private UUID id;

	private String pincode;

	private String country;

	private String state;

	private String city;

	private String street;
	
	public Address(String pincode, String country, String state, String city, String street) {
		super();
		this.id = UUID.randomUUID();
		this.pincode = pincode;
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", pincode=" + pincode + ", country=" + country + ", state=" + state + ", city="
				+ city + ", street=" + street + "]";
	}

	
	
}
