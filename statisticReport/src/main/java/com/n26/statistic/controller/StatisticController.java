package com.n26.statistic.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistic.model.Statistic;
import com.n26.statistic.model.Transaction;
import com.n26.statistic.service.StatisticService;

/**
 * @author Puru
 *
 */
@RestController
@RequestMapping(value = "/api")
public class StatisticController {
	
	@Autowired
	StatisticService statisticService;
	
	@RequestMapping(value= "/addtransaction", method = RequestMethod.POST)
	public synchronized ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
		Instant instant = Instant.now();
        long currentTime = instant.toEpochMilli();

        if(transaction.getTimestamp()> (currentTime-60000)){
        	statisticService.addTransaction(transaction);
            return ResponseEntity.status(201).build();
        }else{
            return ResponseEntity.status(204).build();
        }
		
	}
	
	@RequestMapping(value= "/getstatistic", method = RequestMethod.GET)
	public ResponseEntity<Statistic> getStatistic() {
		Statistic stat = statisticService.getStatistics();
		ResponseEntity<Statistic> statResponse = new ResponseEntity<Statistic>(stat, HttpStatus.OK);
		return statResponse;
	}

}
