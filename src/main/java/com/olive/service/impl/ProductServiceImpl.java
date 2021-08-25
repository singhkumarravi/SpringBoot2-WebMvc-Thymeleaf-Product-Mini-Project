package com.olive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.olive.custom.exception.ProductException;
import com.olive.model.Product;
import com.olive.repo.ProductRepo;
import com.olive.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo repo;

	@Override
	public Product saveProdById(Product prod) {
		Double pcost = prod.getProdCost();
		double gst =(pcost *10)/100;
		prod.setProdGst(gst);
		double totalcost=pcost+gst;
		double discount=(totalcost*40)/100;
		prod.setProdDiscount(discount);        
		return repo.save(prod);
	}

	@Override
	public List<Product> getAllProd() {
		return repo.findAll();
	}

	@Override
	public void deleteProdById(Integer id) {
		repo.deleteById(id);
	}
	@Override
	public Product getOneProdById(Integer id) {
		Product p=null;
		Optional<Product> op = repo.findById(id);
		if(op.isPresent()) {
			 p = op.get();
		}
		else {
			throw new ProductException("Product not exit with Id " + id);
		}
		return p;
	}

	@Override
	public Page<Product> getAllProduct(Pageable pageable) {
		
		return repo.findAll(pageable);
	}

	@Override
	public Boolean isExitProdCode(String code) {
	      Integer count = repo.getProdeCodeByCount(code);
	       boolean exit=count>0 ? true:false;
		return exit;
	}
	
@Override
public Optional<Product> getProdById(Integer id) {

	return repo.findById(id);
}

}
