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
	
	public List<Object[]> overallSales(String from, String to) {
		return transrepo.overAllSalesInMonthly(from, to);
	}
	
	public List<Object[]> itemwiseSales(String from, String to) {
		return transrepo.itemWiseOverAllSales(from, to);
	}
	
	public List<Object[]> recentTransactions(String from, String to) {
		return transrepo.findAllTransactions(from, to);
	}
	
	public List<Object[]> transactionDetails(Long id) {
		return transrepo.findTransactionDetails(id);
	}
}
