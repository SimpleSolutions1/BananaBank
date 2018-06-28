package com.bank.BananaBank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BananaBank.model.dto.TokenDTO;
import com.bank.BananaBank.model.User;
import com.bank.BananaBank.repository.UserRepository;
import com.bank.BananaBank.service.TokenService;

@RestController
@RequestMapping("/tokens")
public class TokenController {

		@Autowired
		UserRepository userRepository;
		
		@Autowired
		TokenService tokenService;
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
		public ResponseEntity<TokenDTO> getUser(@PathVariable("id") Integer id) {
	    	User user = userRepository.getOne(id);
	    	if(user == null){
				throw new RuntimeException("User not found"); 
			}
	    	String randomToken = tokenService.createToken();
	    	TokenDTO token = new TokenDTO();
	    	token.setToken(randomToken);
	    	user.setToken(randomToken);
	    	userRepository.save(user);
	    	return new ResponseEntity<TokenDTO>(token, HttpStatus.CREATED);
	    }


}
