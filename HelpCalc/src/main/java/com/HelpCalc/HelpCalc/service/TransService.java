package com.HelpCalc.HelpCalc.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dao.Transactiondao;
import com.HelpCalc.HelpCalc.dto.Transactions;

@Service
public class TransService {
	
	@Autowired
	Transactiondao transdao;
	
	public ResponseEntity<ResponseStructure<Transactions>> saveTransaction(Transactions transaction) {
		ResponseStructure<Transactions> responseStructure = new ResponseStructure<>();
		if(transaction != null) {
			transaction.setTransactionDate(LocalDateTime.now());
			Transactions savedTransaction = transdao.saveTransaction(transaction);
			if(savedTransaction != null) {
				responseStructure.setStatusCode(200);
				responseStructure.setMessage("Transaction saved successfully");
				responseStructure.setData(savedTransaction);
				return ResponseEntity.ok(responseStructure);
			} else {
				responseStructure.setStatusCode(404);
				responseStructure.setMessage("Failed to save transaction");
				return ResponseEntity.status(500).body(responseStructure);
			}
		}
		responseStructure.setStatusCode(404);
		responseStructure.setMessage("Invalid transaction data");
		return ResponseEntity.status(400).body(responseStructure);
	}
}
