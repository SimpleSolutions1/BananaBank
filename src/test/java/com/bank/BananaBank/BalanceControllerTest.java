package com.bank.BananaBank;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BalanceControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testIncreaseValue() throws Exception{
		String body = "{\"value\": 100}";
		mockMvc.perform(post("/balance/increase/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testIncreaseValueWithException() throws Exception{
	
		MvcResult result = mockMvc.perform(post("/tokens/user/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
			
		JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
		String body = "{\"value\": 600, \"token\": " + jsonObject.get("token") + "}";
		mockMvc.perform(post("/balance/decrease/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUserDoesNotHaveToken() throws Exception{
		String body = "{\"value\": 100}";
		mockMvc.perform(post("/balance/decrease/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(content().string(containsString("You must create your own token ")));
	}
}

