package com.HelpCalc.HelpCalc.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dto.Expense;
import com.HelpCalc.HelpCalc.dto.Items;
import com.HelpCalc.HelpCalc.service.MasterService;
@CrossOrigin(origins = {"http://localhost:3000", "https://localhost"})
@RestController
@RequestMapping
public class masterController {
	
	@Autowired
	MasterService s;
	
	@GetMapping("getAllItems")
	public ResponseEntity<ResponseStructure<ArrayList<Items>>> getAllItems(@RequestParam Long userid) {
		return s.getAllItems(userid);
	}
	
	@PostMapping("saveItem/{id}")
	public ResponseEntity<ResponseStructure<Items>> saveItem(@RequestBody Items item, @PathVariable Long id) {
		return s.saveItem(item,id);
	}
	
	@DeleteMapping("deleteItem/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteItem(@PathVariable Long id) {
		return s.deleteItem(id);
	}
	
	@PutMapping("updateItem/{id}")
	public ResponseEntity<ResponseStructure<Items>> updateItem(@RequestBody Items item,@PathVariable Long id) {
		return s.updateItem(item, id);
	}
	
	@GetMapping("getAllExpenses")
	public ResponseEntity<ResponseStructure<ArrayList<Expense>>> getAllExpenses(@RequestParam Long userid) {
		return s.getAllExpenses(userid);
	}
	
	@PostMapping("saveExpense/{userid}")
	public ResponseEntity<ResponseStructure<Expense>> saveExpense(@RequestBody Expense expense,@PathVariable Long userid) {
		return s.saveExpense(expense, userid);
	}
	
	@DeleteMapping("deleteExpense/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteExpense(@PathVariable Long id) {
		return s.deleteExpense(id);
	}
	
	@PutMapping("updateExpense/{id}")
	public ResponseEntity<ResponseStructure<Expense>> updateExpense(@RequestBody Expense expense, @PathVariable Long id) {
		return s.updateExpense(expense, id);
	}
	
	@GetMapping("getItemBySearch")
	public ResponseEntity<ResponseStructure<ArrayList<Items>>> getItemBySearch(@RequestParam String input) {
		return s.getItemBySearch(input);
	}
	
	@GetMapping("getExpenseBySearch")
	public ResponseEntity<ResponseStructure<ArrayList<Expense>>> getExpenseBySearch(@RequestParam String input) {
		return s.getExpenseBySearch(input);
	}

}
