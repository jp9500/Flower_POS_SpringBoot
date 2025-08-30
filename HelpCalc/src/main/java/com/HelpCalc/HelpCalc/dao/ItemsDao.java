package com.HelpCalc.HelpCalc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.HelpCalc.HelpCalc.dto.Items;
import com.HelpCalc.HelpCalc.repo.ItemsRepo;

@Repository
public class ItemsDao {
	
	@Autowired
	ItemsRepo itemsRepo;
	
	public Items saveItem(Items item) {
		return itemsRepo.save(item);
	}
	
	public Items findByItemName(String itemName) {
		return itemsRepo.findByItemName(itemName);
	}
	
	public Items findById(Long id) {
		Optional<Items> items = itemsRepo.findById(id);
		if (items.isPresent()) {
			return items.get();
		}
		return null;
	}
	
	public Items deleteById(Long id) {
		Items item = findById(id);
		if(item!= null) {
			itemsRepo.deleteById(id);
			return item;
		}
		return null;
	}
	
	public Items updateItem(Items item, Long id) {
		Items exitem = findById(id);
		if(exitem!= null) {
			item.setItemId(exitem.getItemId()); 
			itemsRepo.save(item);
			return item;
		}
		return null;
	}
	
	public ArrayList<Items> getAllItems() {
		return (ArrayList<Items>) itemsRepo.findAll();
	}

	public ArrayList<Items> getItemBySearch(String input) {
	    return new ArrayList<>(
	        itemsRepo.findAll().stream()
	            .filter(item -> item.getItemName().toLowerCase().contains(input.toLowerCase()))
	            .toList()
	    );
	}
}
