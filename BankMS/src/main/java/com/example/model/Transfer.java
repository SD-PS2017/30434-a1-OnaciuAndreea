package com.example.model;

public class Transfer {
	String cnp;
	Integer fromId;
	Integer toId;
	Integer money;
	
	public Transfer(){
		this("",0,0,0);
	}
	
	public Transfer(String cnp, Integer fromId, Integer toId, Integer money) {
		this.cnp = cnp;
		this.fromId = fromId;
		this.toId = toId;
		this.money = money;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	
}
