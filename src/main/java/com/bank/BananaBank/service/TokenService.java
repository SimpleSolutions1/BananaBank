package com.bank.BananaBank.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
	
	private static final int TOKEN_LENGTH = 8;
	
	public String createToken(){
		RandomStringUtils random = new RandomStringUtils();
		return RandomStringUtils.randomAlphabetic(TOKEN_LENGTH);
	}
	
	
}
