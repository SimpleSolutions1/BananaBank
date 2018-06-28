package com.bank.BananaBank.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BalanceDTO implements Serializable{

	private Integer value;

	public BalanceDTO() {
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	

	

}
