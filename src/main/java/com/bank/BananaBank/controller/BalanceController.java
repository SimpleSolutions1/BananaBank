package com.bank.BananaBank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BananaBank.exception.UserNotFoundException;
import com.bank.BananaBank.model.TransactionType;
import com.bank.BananaBank.model.User;
import com.bank.BananaBank.model.dto.BalanceDTO;
import com.bank.BananaBank.model.dto.BalanceDecreaseDTO;
import com.bank.BananaBank.repository.TransactionRepository;
import com.bank.BananaBank.repository.UserRepository;
import com.bank.BananaBank.service.BalanceService;

@RestController
@RequestMapping("/balance")
public class BalanceController  {
	List<User> userList = new ArrayList<User>();
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BalanceService balanceService;
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<BalanceDTO> getUser(@PathVariable("id") Integer id) {

		boolean userExists = userRepository.existsById(id);
	
		if(!userExists){
			throw new UserNotFoundException(id);
		}
		User user = userRepository.getOne(id);
    	Integer value = user.getValue();
    	BalanceDTO balance = new BalanceDTO();
    	balance.setValue(value);
    	return new ResponseEntity<BalanceDTO>(balance, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/increase/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void  increseValue(@PathVariable("id") Integer id, @RequestBody @Valid BalanceDTO balance,
            BindingResult bindingResult) {

		boolean userExists = userRepository.existsById(id);
		
		if(!userExists){
			throw new UserNotFoundException(id);
		}
		
		User user = userRepository.getOne(id);
		
		Integer valueBeforeIncrease = user.getValue();
		Integer valueFromRequest = balance.getValue();
		
		balanceService.updateTransaction(user, valueFromRequest, TransactionType.INCREASE);
    	user.setValue(balanceService.sumValue(valueBeforeIncrease, valueFromRequest));
    	userRepository.save(user);
    
    }
	
	@RequestMapping(value = "/decrease/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public void decreaseValue(@PathVariable("id") Integer id, @RequestBody @Valid BalanceDecreaseDTO balanceDecrease,
            BindingResult bindingResult) {

		boolean userExists = userRepository.existsById(id);
		
		if(!userExists){
			throw new UserNotFoundException(id);
		}
		
		User user = userRepository.getOne(id);
		
		Integer valueBefore = user.getValue();
		String tokenFromDataBase = user.getToken();
		Integer valueFromRequest = balanceDecrease.getValue();
		String tokenFromRequest = balanceDecrease.getToken();
		
		if(tokenFromDataBase == null){
			throw new RuntimeException("You must create your own token ");
		}
		if(!balanceService.checkValueAfterDecrease(valueBefore, valueFromRequest)){
			throw new RuntimeException("Do not have enough money. You have only "+valueBefore); 
		}
		if(!balanceService.compareTokens(tokenFromDataBase, tokenFromRequest)){
			throw new RuntimeException("Tokens are not the same"); 
		}
		
		balanceService.updateTransaction(user, valueFromRequest, TransactionType.DECREASE);
		user.setValue(balanceService.sumValue(valueBefore, -valueFromRequest));
    	userRepository.save(user);
    
    }

	
		
	

	
}
