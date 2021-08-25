package com.olive.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.olive.model.Product;

public interface ProductService {
	
	public Product saveProdById(Product prod);
	public List<Product> getAllProd();
	public void deleteProdById(Integer id);
	public Product getOneProdById(Integer id);
	public Optional<Product> getProdById(Integer id);
	public Page<Product> getAllProduct(Pageable pageable );
	
	public Boolean isExitProdCode(String code);

}
 