package com.bank.BananaBank;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import com.bank.BananaBank.model.User;
import com.bank.BananaBank.model.dto.TokenDTO;
import com.bank.BananaBank.repository.TransactionRepository;
import com.bank.BananaBank.repository.UserRepository;
import com.bank.BananaBank.service.BalanceService;
import com.bank.BananaBank.service.TokenService;


public class BalanceServiceTest {

	private User user;

	@Mock
	UserRepository userRepository;
	@Mock
	TransactionRepository transactionRepository;
	@Mock
	BalanceService balanceService;
	
	@Mock
	TokenService tokenService;
	
	@Before
	public void setUp(){
		balanceService = new BalanceService();
		tokenService = new TokenService();
		user = new User();
		user.setId(5);
		user.setValue(Integer.valueOf(100));
		MockitoAnnotations.initMocks(this);
		when(userRepository.getOne(user.getId())).thenReturn(user);
	}
	

	@Test
	public void testShouldDecreaseValue(){
		Assert.assertEquals(Integer.valueOf(100),user.getValue(), 0);
		String tokenToEncode = tokenService.createToken();
		TokenDTO token = new TokenDTO();
		token.setToken(tokenToEncode);
	
		Assert.assertNotNull(tokenToEncode);
		user.setToken(tokenToEncode);
		userRepository.save(user);
	
		
		boolean isAmountGreatherThanRequestValue = balanceService.checkValueAfterDecrease(user.getValue(), Integer.valueOf(100));
		Assert.assertFalse(isAmountGreatherThanRequestValue);
		user.setValue(balanceService.sumValue(user.getValue(), -Integer.valueOf(100)));
		userRepository.save(user);
		Assert.assertEquals(new Integer(0), user.getValue());
	
	}
}
