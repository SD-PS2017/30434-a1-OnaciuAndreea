package com.example.dto;

import java.sql.Date;


public class AccountDTO {
	int id;
	Date date_of_Creation;
	int money;
	String type;
	String cnp;
	
	
	public AccountDTO(int money,String type,String cnp){
		this(null,money,type,cnp);
	}

	public AccountDTO(int id,String cnp){
		this(id,null,0,"",cnp);
	}
	
	public AccountDTO(Date date_of_Creation, int money, String type,String cnp) {
		this.date_of_Creation = date_of_Creation;
		this.money = money;
		this.type = type;
		this.cnp=cnp;
	}
	
	public AccountDTO(int id, int money, String type,String cnp) {
		this.id = id;
		this.money = money;
		this.type = type;
		this.cnp=cnp;
	}
	
	public AccountDTO(int id, Date date_of_Creation, int money, String type, String cnp) {
		this.id = id;
		this.date_of_Creation = date_of_Creation;
		this.money = money;
		this.type = type;
		this.cnp = cnp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccountDTO(){
	    this(null,0,"","");
	}
	
	
	public Date getDate_of_Creation() {
		return date_of_Creation;
	}
	public void setDate_of_Creation(Date date_of_Creation) {
		this.date_of_Creation = date_of_Creation;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCnp() {
		return cnp;
	}


	public void setCnp(String cnp) {
		this.cnp = cnp;
	}


	@Override
	public String toString() {
		return "AccountDTO [date_of_Creation=" + date_of_Creation + ", money=" + money + ", type=" + type + "]";
	}

}
