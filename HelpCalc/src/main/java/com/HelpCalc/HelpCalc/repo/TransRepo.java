package com.HelpCalc.HelpCalc.repo;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HelpCalc.HelpCalc.dto.Transactions;

public interface TransRepo extends JpaRepository<Transactions, Long>{

	@Query(value = "SELECT SUM(subtotal),SUM(grand_total),SUM(commission_total) FROM transactions "
			+ "WHERE MONTH(transaction_date)= :month "
			+ "GROUP BY MONTH(transaction_date) ", nativeQuery = true)
	Object[] overAllSalesInMonthly(int month);
	
	@Query(value = "SELECT item_name,SUM(total)  "
			+ "FROM `transaction_smry` a  "
			+ "INNER JOIN `transactions` c ON a.transaction_id=c.transaction_id "
			+ "INNER JOIN `items` b ON a.item_id=b.item_id AND c.userid=b.userid "
			+ "WHERE MONTH(transaction_date)= :month "
			+ "GROUP BY MONTH(transaction_date),item_name", nativeQuery = true)
	List<Map<String,Double>> itemWiseOverAllSales(int itemid, int month);
	
	
}
