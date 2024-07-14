package com.example.myproject.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.example.myproject.dao.ProductDao;
import com.example.myproject.entity.Product;

@Controller
public class MyController {

	@Autowired
	private ProductDao productDao;
	
    @RequestMapping("/")
    public String home(Model m) {
    	List<Product> product = productDao.getProducts();
    	m.addAttribute("product", product);
    	
        return "myPage"; 
    }
    
    @RequestMapping("/addProduct")
    public String addProduct(Model m) {
    	m.addAttribute("title", "Add Product");
    	return "addProductForm";
    }
    
    @RequestMapping(value="/handle-product", method=RequestMethod.POST)
    public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request) {
    	System.out.println(product);
    	productDao.createProduct(product);
    	RedirectView redirectView = new RedirectView();
    	redirectView.setUrl(request.getContextPath() + "/");
    	return redirectView;
    }
    
    @RequestMapping("/delete/{productId}")
    public RedirectView deleteProduct(@PathVariable("productId") Long pid, HttpServletRequest request) {
    	productDao.deleteProduct(pid);
    	RedirectView redirectView = new RedirectView();
    	redirectView.setUrl(request.getContextPath() + "/");
    	return redirectView;
    }
    
    @RequestMapping(value="/update/{productId}")
    public String updateProduct(@PathVariable("productId") Long pid, Model m) {
    	Product product = this.productDao.getProduct(pid);
    	m.addAttribute("product", product);
    	return "updateProduct";
    	
    }
    
}
