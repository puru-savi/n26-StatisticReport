package com.n26.statistic.serviceimpl;

import java.time.Instant;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.n26.statistic.model.Statistic;
import com.n26.statistic.model.Transaction;
import com.n26.statistic.service.InMemoryStorageService;

/**
 * @author Puru
 *
 */
@Service
public class InMemoryStorageServiceImpl implements InMemoryStorageService{
	
	private long count;
    private double max;
    private double avg;
    private double min;
    private double sum;
    Instant instant;
    long currentTime;

    public ConcurrentHashMap<Long,Double> transactionData;

    public InMemoryStorageServiceImpl(){
    	transactionData = new ConcurrentHashMap<>();
    }

	@Override
	public synchronized void addTransaction(Transaction transaction) {
		transactionData.put(transaction.getTimestamp(), transaction.getAmount());
	}

	@Override
	public Statistic getStatistic() {
		double value;
		this.max = Long.MIN_VALUE;
        this.min = Long.MAX_VALUE;
        this.avg = 0;
        this.sum = 0;
        this.count = 0;
        
        this.instant = Instant.now();
        this.currentTime = instant.toEpochMilli();
        
		for(HashMap.Entry<Long,Double> entry: transactionData.entrySet()){
            if(entry.getKey()> currentTime - 60000){
                value = entry.getValue();

                if(this.max<value){
                    this.max = value;
                }

                if(this.min > value){
                    this.min = value;
                }

                this.sum = this.sum + value;
                this.count++;
            }
        }

        if(!(this.count==0)){
            this.avg = this.sum/this.count;
        }else{
            this.avg = 0;
        }
		return new Statistic(this.sum, this.avg, this.max, this.min, this.count);
    }

	@Override
	public void clearTransactions() {
		// TODO Auto-generated method stub
		
	}

}
