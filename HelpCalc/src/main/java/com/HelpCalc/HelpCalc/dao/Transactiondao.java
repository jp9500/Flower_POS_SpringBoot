package com.HelpCalc.HelpCalc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HelpCalc.HelpCalc.dto.Transactions;
import com.HelpCalc.HelpCalc.repo.TransRepo;

@Repository
public class Transactiondao {
	
	@Autowired
	TransRepo transrepo;
	
	public Transactions saveTransaction(Transactions transaction) {
		return transrepo.save(transaction);
	}
	
	public Object[] overallSales(int month) {
		return transrepo.overAllSalesInMonthly(month);
	}
}
