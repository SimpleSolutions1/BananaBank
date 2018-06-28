package com.bank.BananaBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BananaBank.model.Transaction;
import com.bank.BananaBank.model.User;
import com.bank.BananaBank.repository.UserRepository;

@RestController
@RequestMapping("/history")
public class HistoryController {

		
		@Autowired
		UserRepository userRepository;
		
		@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
		public ResponseEntity<List<Transaction>> getHistory(@PathVariable("id") Integer id) {
	    	User user = userRepository.getOne(id);
	    	if(user == null){
				throw new RuntimeException("User not found"); 
			}
	    	List<Transaction> transactions = user.getTransactions();
	    	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	    }


}
