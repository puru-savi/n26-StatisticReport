package com.n26.statistic.controller;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.n26.statistic.model.Transaction;

import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	
	@Test
	public void Should_ReturnHTTPStatus201_ForValidTimeStamp() throws Exception {
		
		 	Instant instant = Instant.now();
	        long currentTime = instant.toEpochMilli();

	        Transaction transaction = new Transaction(32.5,currentTime);

	        this.mockMvc.perform(post("/api/addtransaction")
	                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transaction))
	        ).andExpect(status().is(201));
	}
	
	@Test
	public void Should_ReturnHTTPStatus204_ForInvalidTimeStamp() throws Exception {
		
	 	Instant instant = Instant.now();
        long currentTime = instant.toEpochMilli();

        Transaction transaction = new Transaction(64.5,currentTime-80000);

        this.mockMvc.perform(post("/api/addtransaction")
                .contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(transaction))
        ).andExpect(status().is(204));
}
	
	@Test
	public void Should_ReturnHTTPStatus200_ForGetStatisticReport() throws Exception {
		
		this.mockMvc.perform(get("/api/getstatistic"))
				.andDo(print()).andExpect(status().isOk());
	}

}
