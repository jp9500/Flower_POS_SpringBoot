package com.HelpCalc.HelpCalc.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HelpCalc.HelpCalc.dto.Items;

public interface ItemsRepo extends JpaRepository<Items, Long> {

	@Query(value = "SELECT * FROM items WHERE userid = :userid ORDER BY item_name ASC ", nativeQuery = true)
	ArrayList<Items> findAllByUserid(Long userid);
	
	Items findByItemName(String itemName);

}
