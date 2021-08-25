package com.olive.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olive.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	/*
	 * It is used for checking for the duplicate code entry
	 */
	@Query("Select count(prodCode) from Product where prodCode=:pcode")
	public Integer getProdeCodeByCount(String pcode);
	

}
 