package com.HelpCalc.HelpCalc.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HelpCalc.HelpCalc.dto.Expense;
import com.HelpCalc.HelpCalc.repo.ExpenseRepo;

@Repository
public class ExpenseDao {
	
	@Autowired
	ExpenseRepo er;
	
	public Expense saveExpense(Expense expense) {
		return er.save(expense);
	} 
	
	public Expense findById(Long id) {
		Optional<Expense> expense = er.findById(id);	
		if (expense.isPresent()) {
			return expense.get();
		}
		return null;
	}
	
	public Expense deleteById(Long id) {
		Expense expense = findById(id);
		if (expense != null) {
			er.deleteById(id);
			return expense;
		}
		return null;
	}
	
	public Expense updateExpense(Expense expense, Long id) {
		Expense existingExpense = findById(id);
		if (existingExpense != null) {
			expense.setExpenseId(existingExpense.getExpenseId());
			return er.save(expense);
		}
		return null;
	}
	
	public ArrayList<Expense> getAllExpenses() {
		return (ArrayList<Expense>) er.findAll();
	}
	
	public Expense findByExpenseName(String expenseName) {
		return er.findByExpenseName(expenseName);
	}
}
