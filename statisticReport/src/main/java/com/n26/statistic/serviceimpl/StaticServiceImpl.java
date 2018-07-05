package com.n26.statistic.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.statistic.model.Statistic;
import com.n26.statistic.model.Transaction;
import com.n26.statistic.service.InMemoryStorageService;
import com.n26.statistic.service.StatisticService;

/**
 * @author Puru
 *
 */
@Service
public class StaticServiceImpl implements StatisticService{
	
	@Autowired
	InMemoryStorageService imsService;
	
	@Override
	public void addTransaction(Transaction transaction) {
		imsService.addTransaction(transaction);
	}

	@Override
	public Statistic getStatistics() {
		return imsService.getStatistic();
	}

}
