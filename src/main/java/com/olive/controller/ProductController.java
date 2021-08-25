package com.olive.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.olive.model.Product;
import com.olive.repo.ProductRepo;
import com.olive.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service; 
	
	@Autowired
	ProductRepo repo;

	@GetMapping("/")
	private String showHome(Model model) {
		
		return "Home";
	}
	
	
	@GetMapping("/reg")
	private String showReg(Model model) {
		model.addAttribute("prod",new Product());
		return "register";
	}
	
	@PostMapping("/reg/save")
	private String saveData(@ModelAttribute Product prod,Model model) {
		Product prods = service.saveProdById(prod);
	    Integer Id = prods.getProdId();
	    String msg="Product save with Id  ::  "+Id ;
	    System.out.println("msg " + msg);
		model.addAttribute("msg", msg);
		return "message";
	}
	
	@GetMapping("/all")
	public String getAllProduct(Model model) {
		     List<Product> list = service.getAllProd();
		     model.addAttribute("list" ,list);
		return "showAll";
		
	}
	
	@GetMapping("/one")
	public String getOneProduct(@RequestParam Integer ids,Model model) {
		     Product prod = service.getOneProdById(ids);
		     model.addAttribute("prod" ,prod);
		     System.out.println(" product :: " + prod);
		return "showAll";
		
	}
	
	@GetMapping("/delete")
	public String deleteProdById(@RequestParam("id")Integer id,Model model) {	
		 service.deleteProdById(id);
		 String msg="Product Id " +id+ "deleted";
		 List<Product> list = service.getAllProd();
	     model.addAttribute("list" ,list);
		 model.addAttribute("msg" ,msg);
		return "showAll";
	}
	
	@GetMapping("/edit")
	public String showEdit(@RequestParam("id")Integer id,Model model) {	
		String path="";
		  Optional<Product> opt = service.getProdById(id);
		  if(!opt.isPresent()) {
			 path="showAll"; 
		  }
		  else {
			  Product prod = opt.get();
	          model.addAttribute("prod" ,prod);
	          path="showEdit";
		  }
		return path;
	}
	
	@PostMapping("/update")
	private String updateData(@ModelAttribute Product prod,Model model) {
		Product prods = service.saveProdById(prod);
	    Integer Id = prods.getProdId();
	    String msg="Product update with Id  ::  "+Id ;
		 model.addAttribute("msg", msg);
		return "redirect:all";
	}
	
	/*
	 * Pagination concept implementing here
	 */
	@GetMapping("/allData")
	public String showAll(@PageableDefault(page=1,size=14)Pageable p,Model model) {
		 Page<Product> page = service.getAllProduct(p);
		 model.addAttribute("list",page.getContent());
		 model.addAttribute("page" ,page);
		return "ProductData";
	}
	
	/*
	 * This logic is used for checking the duplicate entry code
	 */
	@GetMapping("/validate")
	@ResponseBody 
	public String validateCode(@RequestParam String prodCode,Model model) {
		String msgs="";
		Boolean exit = service.isExitProdCode(prodCode);
		if(exit) {
			         msgs= prodCode +" , Alredy exit try different"; 
			         
		         }
		model.addAttribute("msgs", msgs);
		return msgs;
	}
	 
}
