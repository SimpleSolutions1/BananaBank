package com.bank.BananaBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bank.BananaBank.model.Transaction;
import com.bank.BananaBank.model.TransactionType;
import com.bank.BananaBank.model.User;
import com.bank.BananaBank.repository.TransactionRepository;
import com.bank.BananaBank.repository.UserRepository;

@Service
public class BalanceService {
     
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
    
	public boolean compareTokens(String tokenFromDataBase, String tokenFromRequest){

		return tokenFromDataBase.equals(tokenFromRequest) ? true : false;
	}

	public Integer sumValue(Integer valueBefore, Integer valueFromRequest){
		return Integer.sum(valueBefore, valueFromRequest);
		
	}
	
	public void updateTransaction(User user, Integer valueFromRequest, TransactionType transactionType ){
    	Transaction transaction = new Transaction(transactionType , valueFromRequest);
    	transaction.setUser(user);
    	transactionRepository.save(transaction);
	}
	
	public boolean checkValueAfterDecrease(Integer valueBefore, Integer valueFromRequest){
		if(valueFromRequest.intValue() > valueBefore.intValue()){
			return false;
		} else {
			return true;
		}
	}
	
	public void checkUser(User user){
		if(user == null){
			throw new RuntimeException("User not found"); 
		}
	}
	
	public void checkResult(BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new RuntimeException("Here is exception "+bindingResult.getFieldError()); 
		}
	}
}
