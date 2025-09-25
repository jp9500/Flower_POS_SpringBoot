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
			if(transaction.getType().equalsIgnoreCase("expense")) 
				transaction.setType("E");
			else 
				transaction.setType("I");
			
			//to map child to parent
		   if (transaction.getSmry() != null) {
	            transaction.getSmry().forEach(smry -> smry.setTransactions(transaction));
	        }
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
	
	public List<Map<String, Object>> overallSales(String from, String to) {
		List<Object[]> results = transdao.overallSales(from, to);
		if(results == null) {
			 return List.of(
			            Map.of("label", "Total", "value", 0),
			            Map.of("label", "Income", "value", 0),
			            Map.of("label", "Expense", "value", 0)
			        );
			}
			Object[] result = results.get(0);
			List<Map<String, Object>> response = new ArrayList<>();
			response.add(Map.of("label", "Total", "value",Math.round( ((Number) result[0]).doubleValue())));
			response.add(Map.of("label", "Income", "value",Math.round( ((Number) result[1]).doubleValue())));
			response.add(Map.of("label", "Expense", "value",Math.round( ((Number) result[2]).doubleValue())));
			
			return response;

	}
	
	
	public List<Map<String, Object>> itemwiseSales(String from, String to) {
		List<Object[]> results = transdao.itemwiseSales(from, to);
		if(results == null) {
			 return List.of(
			            Map.of("label", "No Data", "value", 0)
			        );
			}
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of("label", result[0], "value",Math.round( ((Number) result[1]).doubleValue())));
			}
			
			return response;
	}
	
	
	public List<Map<String, Object>> getExpenseWiseSales(String from, String to) {
		List<Object[]> results = transdao.expenseWiseSales(from, to);
		if(results == null) {
			 return List.of(
			            Map.of("label", "No Data", "value", 0)
			        );
			}
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of("label", result[0], "value",Math.round( ((Number) result[1]).doubleValue())));
			}
			
			return response;
	}
	
	// sales transaction
	public List<Map<String, Object>> recentTransactions(String from, String to) {
		List<Object[]> results = transdao.recentTransactions(from, to);
		if(results == null) {
			 return null;
			}
			
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of(
						"id", result[0],
						"date", result[1].toString(),
						"items", ((Number) result[2]).intValue(),
						"amount", Math.round( ((Number) result[3]).doubleValue()),
						"commTotal", Math.round( ((Number) result[4]).doubleValue())
						));
			}
			return response;
	}
	
	// Expense Transaction
	public List<Map<String, Object>> getExpenseTransactions(String from, String to) {
		List<Object[]> results = transdao.getExpenseTransactions(from, to);
		if(results == null) {
			 return null;
			}
			
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of(
						"id", result[0],
						"date", result[1].toString(),
						"items", ((Number) result[2]).intValue(),
						"amount", Math.round( ((Number) result[3]).doubleValue())
						));
			}
			return response;
	}
	
	public List<Map<String, Object>> transactionDetails(Long id) {
		List<Object[]> results = transdao.transactionDetails(id);
		if(results == null) {
			 return new ArrayList<>();
			}
			
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of(
						"itemName", result[0],
						"qty", ((Number) result[1]).intValue()+" ~ "+result[4],
						"price", Math.round( ((Number) result[2]).doubleValue()),
						"total", Math.round( ((Number) result[3]).doubleValue())
						));
			}
			return response;
	}
	
	
	public List<Map<String, Object>> getExpenseTransactionDetails(Long id) {
		List<Object[]> results = transdao.findExpenseTransactionDetails(id);
		if(results == null) {
			 return new ArrayList<>();
			}
			
			List<Map<String, Object>> response = new ArrayList<>();
			for(Object[] result : results) {
				response.add(Map.of(
						"expenseName", result[0],
						"qty", ((Number) result[1]).intValue()+" ~ "+result[4],
						"price", Math.round( ((Number) result[2]).doubleValue()),
						"total", Math.round( ((Number) result[3]).doubleValue())
						));
			}
			return response;
	}
	
}
