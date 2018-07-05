package com.n26.statistic.service;

import org.springframework.stereotype.Service;

import com.n26.statistic.model.Statistic;
import com.n26.statistic.model.Transaction;

/**
 * @author Puru
 *
 */
@Service
public interface StatisticService {
	
	public void addTransaction(Transaction transaction);
	
	public Statistic getStatistics();

}
