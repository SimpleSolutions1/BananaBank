package com.bank.BananaBank.model.dto;

import java.time.LocalTime;

public class ErrorMessageDTO {

	private int status;
	private String message;
	private LocalTime time;
	
	
	
	public ErrorMessageDTO(int status, String message) {
		this.status = status;
		this.message = message;
		this.time = LocalTime.now();
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	

	
}
