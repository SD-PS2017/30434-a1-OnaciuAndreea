package com.example.dto;

public class ClientDTO {
	String cnp;
	Integer identity_Card_number;
	String name;
	String address;
	public ClientDTO(String cnp, Integer identity_Card_number, String name, String address) {
		super();
		this.cnp = cnp;
		this.identity_Card_number = identity_Card_number;
		this.name = name;
		this.address = address;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public Integer getIdentity_Card_number() {
		return identity_Card_number;
	}
	public void setIdentity_Card_number(Integer identity_Card_number) {
		this.identity_Card_number = identity_Card_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ClientDTO [cnp=" + cnp + ", identity_Card_number=" + identity_Card_number + ", name=" + name
				+ ", address=" + address + "]";
	}
	
	
}
