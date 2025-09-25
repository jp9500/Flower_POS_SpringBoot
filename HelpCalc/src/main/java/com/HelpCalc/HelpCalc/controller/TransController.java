package com.HelpCalc.HelpCalc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dto.Transactions;
import com.HelpCalc.HelpCalc.service.TransService;

@CrossOrigin(origins = {"http://localhost:3000", "https://localhost"})
@RestController
@RequestMapping
public class TransController {
	
	@Autowired
	TransService transservice;
	
	@PostMapping("saveTransaction")
	public ResponseEntity<ResponseStructure<Transactions>> saveTransaction(@RequestBody Transactions transaction) {
		return transservice.saveTransaction(transaction);
	}
	
	@GetMapping("overallSales")
	public List<Map<String, Object>> overallSales(@RequestParam String from, @RequestParam String to) {
		return transservice.overallSales(from, to);
	}
	
	@GetMapping("itemwiseSales")
	public List<Map<String, Object>> itemwiseSales(@RequestParam String from, @RequestParam String to) {
		return transservice.itemwiseSales(from, to);
	}
	
	@GetMapping("expenseWiseSales")
	public List<Map<String, Object>> getExpenseWiseSales(@RequestParam String from, @RequestParam String to){
		return transservice.getExpenseWiseSales(from, to);
	}
	
	@GetMapping("recentTransactions")
	public List<Map<String, Object>> recentTransactions(@RequestParam String from, @RequestParam String to) {
		return transservice.recentTransactions(from, to);
	}
	
	@GetMapping("getExpenseTransactions")
	public List<Map<String, Object>> getExpenseTransactions(@RequestParam String from, @RequestParam String to) {
		return transservice.getExpenseTransactions(from, to);
	}
	
	@GetMapping("transactionDetails")
	public List<Map<String, Object>> transactionDetails(@RequestParam Long id) {
		return transservice.transactionDetails(id);
	}
	
	@GetMapping("getExpenseTransactionDetails")
	public List<Map<String, Object>> getExpenseTransactionDetails(@RequestParam Long id) {
		return transservice.getExpenseTransactionDetails(id);
	}
	
	
}
