package com.bank.BananaBank.model.dto;

import java.io.Serializable;

public class BalanceDecreaseDTO implements Serializable {

	private Integer value;
	private String Token;

	public BalanceDecreaseDTO() {
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
