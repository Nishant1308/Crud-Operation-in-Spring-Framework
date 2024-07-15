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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

import com.example.myproject.dao.ProductDao;
import com.example.myproject.entity.Product;

@Controller
@SessionAttributes("message")
public class MyController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model m, @SessionAttribute(name = "message", required = false) String message,
			SessionStatus sessionStatus) {
		List<Product> product = productDao.getProducts();
		m.addAttribute("product", product);

		if (message != null) {
			m.addAttribute("message", message);
			sessionStatus.setComplete();
		}
		return "myPage";
	}

	@RequestMapping("/addProduct")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add Product");
		return "addProductForm";
	}

	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request, Model m) {
		productDao.createProduct(product);
		m.addAttribute("message", "Product Added Successfully");
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}

	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") Long pid, HttpServletRequest request, Model m) {
		productDao.deleteProduct(pid);
		m.addAttribute("message", "Product Deleted Successfully ");
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}

	@RequestMapping(value = "/update/{productId}")
	public String updateProduct(@PathVariable("productId") Long pid, Model m) {
		Product product = this.productDao.getProduct(pid);
		m.addAttribute("product", product);
		return "updateProduct";
	}
}