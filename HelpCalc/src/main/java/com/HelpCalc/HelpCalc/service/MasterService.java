package com.HelpCalc.HelpCalc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.HelpCalc.HelpCalc.config.ResponseStructure;
import com.HelpCalc.HelpCalc.dao.ExpenseDao;
import com.HelpCalc.HelpCalc.dao.ItemsDao;
import com.HelpCalc.HelpCalc.dto.Expense;
import com.HelpCalc.HelpCalc.dto.Items;
import com.HelpCalc.HelpCalc.dto.User;
import com.HelpCalc.HelpCalc.repo.UserRepo;

@Service
public class MasterService {
	
	@Autowired
	ItemsDao idao;
	
	@Autowired
	ExpenseDao edao;
	
	@Autowired
	UserRepo ur;
	
	// Item related methods
	
	public ResponseEntity<ResponseStructure<ArrayList<Items>>> getAllItems() {
		ResponseStructure<ArrayList<Items>> responseStructure = new ResponseStructure<>();
		ArrayList<Items> itemsList = idao.getAllItems(); 
		if (itemsList != null && !itemsList.isEmpty()) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Items retrieved successfully");
			responseStructure.setData(itemsList);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("No items found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteItem(Long id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Items item = idao.deleteById(id);
		if (item != null) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Item deleted successfully");
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Item not found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<Items>> updateItem(Items item, Long id) {
		ResponseStructure<Items> responseStructure = new ResponseStructure<>();
		Items updatedItem = idao.updateItem(item, id);
		if (updatedItem != null) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Item updated successfully");
			responseStructure.setData(updatedItem);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Item not found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<Items>> saveItem(Items item,long id) {
		ResponseStructure<Items> responseStructure = new ResponseStructure<>();
		Items itemByName = idao.findByItemName(item.getItemName());
		User exuser = ur.findById(id).orElse(null);
		if(itemByName != null) {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Item with this name already exists");
			return ResponseEntity.status(400).body(responseStructure);
		}
		if(exuser!=null) {
			item.setUser(exuser);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("User not found");
			return ResponseEntity.status(404).body(responseStructure);
		}
		Items savedItem = idao.saveItem(item);
		if (savedItem != null) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Item saved successfully");
			responseStructure.setData(savedItem);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Failed to save item");
			return ResponseEntity.status(500).body(responseStructure);
		}
	}
	
	
	// Expense related methods
	
	public ResponseEntity<ResponseStructure<ArrayList<Expense>>> getAllExpenses() {
		ResponseStructure<ArrayList<Expense>> responseStructure = new ResponseStructure<>();
		ArrayList<Expense> expensesList = edao.getAllExpenses();
		if (expensesList != null && !expensesList.isEmpty()) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Expenses retrieved successfully");
			responseStructure.setData(expensesList);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("No expenses found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<Expense>> saveExpense(Expense expense) {
		ResponseStructure<Expense> responseStructure = new ResponseStructure<>();
		Expense expenseByName = edao.findByExpenseName(expense.getExpenseName());
		if (expenseByName != null) {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Expense with this name already exists");
			return ResponseEntity.status(400).body(responseStructure);
		}
		Expense savedExpense = edao.saveExpense(expense);
		if (savedExpense != null) {
			responseStructure.setStatusCode(0);
			responseStructure.setMessage("Expense saved successfully");
			responseStructure.setData(savedExpense);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Failed to save expense");
			return ResponseEntity.status(500).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<Expense>> updateExpense(Expense expense, Long id) {
		ResponseStructure<Expense> responseStructure = new ResponseStructure<>();
		Expense updatedExpense = edao.updateExpense(expense, id);
		if (updatedExpense != null) {
			responseStructure.setStatusCode(200);
			responseStructure.setMessage("Expense updated successfully");
			responseStructure.setData(updatedExpense);
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(404);
			responseStructure.setMessage("Expense not found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteExpense(Long id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		Expense expense = edao.deleteById(id);
		if (expense != null) {
			responseStructure.setStatusCode(200);
			responseStructure.setMessage("Expense deleted successfully");
			return ResponseEntity.ok(responseStructure);
		} else {
			responseStructure.setStatusCode(1);
			responseStructure.setMessage("Expense not found");
			return ResponseEntity.status(404).body(responseStructure);
		}
	}

	public ResponseEntity<ResponseStructure<ArrayList<Items>>> getItemBySearch(String input) {
		if (input != null && !input.isEmpty()) {
			ResponseStructure<ArrayList<Items>> responseStructure = new ResponseStructure<>();
			ArrayList<Items> itemsList = idao.getItemBySearch(input);
			if (itemsList != null && !itemsList.isEmpty()) {
				responseStructure.setStatusCode(200);
				responseStructure.setMessage("Items found for search input");
				responseStructure.setData(itemsList);
				return ResponseEntity.ok(responseStructure);
			} else {
				responseStructure.setStatusCode(400);
				responseStructure.setMessage("No items found for the search input");
				return ResponseEntity.status(404).body(responseStructure);
			}
		}
		return null;
	}
	
	
	
}
