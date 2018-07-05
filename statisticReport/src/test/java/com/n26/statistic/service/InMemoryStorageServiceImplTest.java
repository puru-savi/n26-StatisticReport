package com.n26.statistic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.statistic.model.Statistic;
import com.n26.statistic.model.Transaction;
import com.n26.statistic.serviceimpl.InMemoryStorageServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InMemoryStorageServiceImplTest {
	
	 @Autowired
	 private InMemoryStorageServiceImpl imsServiceImpl;
	
	@Test
	public void Should_addTransaction_ForValidTimeStamp() throws Exception {
		
		Instant instant = Instant.now();
        long currentTime = instant.toEpochMilli();
		//check initial size of hashmap without add transaction operation.
        int InitialSizeOfTransaction = imsServiceImpl.transactionData.size();
        
		Transaction transaction = new Transaction(30.5, currentTime);
		imsServiceImpl.addTransaction(transaction);
		 
		//check current size of hashmap after add transactoin operation
		int currentSizeOfTransaction = imsServiceImpl.transactionData.size();
		
		//for successful operatin current size of hashmap should be greater than its initial size.
		assertTrue(currentSizeOfTransaction > InitialSizeOfTransaction);
	}
	
	@Test
	public void Should_getStatistic_ForValidTimeStamp() throws Exception {
		
		Instant instant = Instant.now();
        long currentTime = instant.toEpochMilli();
        //minimum value
		imsServiceImpl.addTransaction(new Transaction(20,currentTime-500)); 
        for(int i=0;i<3;i++){
        	imsServiceImpl.addTransaction(new Transaction(50,currentTime-(i)));
        }
        //maximum value
        imsServiceImpl.addTransaction(new Transaction(80,currentTime-2000)); 
        
        Statistic stats = imsServiceImpl.getStatistic();

        assertEquals(80,stats.getMax(),0);
        assertEquals(20,stats.getMin(),0);
        assertEquals(50,stats.getAvg(),0);
        assertEquals(5,stats.getCount(),0);
        assertEquals(250,stats.getSum(),0);
	}
}
