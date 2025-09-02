package com.HelpCalc.HelpCalc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	public List<Map<String, Object>> overallSales(int month) {
		List<Object[]> results = transdao.overallSales(month);
		if(results == null) {
			 return List.of(
			            Map.of("label", "Subtotal", "value", 0),
			            Map.of("label", "Grand Total", "value", 0),
			            Map.of("label", "Commission", "value", 0)
			        );
			}
			Object[] result = results.get(0);
			List<Map<String, Object>> response = new ArrayList<>();
			response.add(Map.of("label", "Subtotal", "value",Math.round( ((Number) result[0]).doubleValue())));
			response.add(Map.of("label", "Grand Total", "value",Math.round( ((Number) result[1]).doubleValue())));
			response.add(Map.of("label", "Commission", "value",Math.round( ((Number) result[2]).doubleValue())));
			
			return response;

	}
}
