package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="adrSequence")
	@SequenceGenerator(name="adrSequence",allocationSize=1, sequenceName="SQ_ADR_PK")
	private int adr_id; // address id
	
	@Column
	private String city;
	
	@Column
	private String state_name;
	
	@Column
	private String str_adr; // street address
	
	@Column
	private int zip; // zip code

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String city, String state_name, String str_adr, int zip) {
		super();
		//this.adr_id = adr_id;
		this.city = city;
		this.state_name = state_name;
		this.str_adr = str_adr;
		this.zip = zip;
	}

	public int getAdr_id() {
		return adr_id;
	}

	public void setAdr_id(int adr_id) {
		this.adr_id = adr_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getStr_adr() {
		return str_adr;
	}

	public void setStr_adr(String str_adr) {
		this.str_adr = str_adr;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adr_id;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state_name == null) ? 0 : state_name.hashCode());
		result = prime * result + ((str_adr == null) ? 0 : str_adr.hashCode());
		result = prime * result + zip;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (adr_id != other.adr_id)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state_name == null) {
			if (other.state_name != null)
				return false;
		} else if (!state_name.equals(other.state_name))
			return false;
		if (str_adr == null) {
			if (other.str_adr != null)
				return false;
		} else if (!str_adr.equals(other.str_adr))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [adr_id=" + adr_id + ", city=" + city + ", state_name=" + state_name + ", str_adr=" + str_adr
				+ ", zip=" + zip + "]";
	}

	
}
