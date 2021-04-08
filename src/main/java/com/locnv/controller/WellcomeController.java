package com.locnv.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locnv.model.Product;
import com.locnv.service.CategoryService;
import com.locnv.service.ProductService;
import com.locnv.service.impl.CategoryServiceImpl;
import com.locnv.service.impl.ProductServiceImpl;
@WebServlet(urlPatterns="/welcome")

public class WellcomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	CategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> productList = productService.getAll();
		req.setAttribute("pwelcome", productList);

		req.getRequestDispatcher("/view/client/view/index.jsp").forward(req, resp);
	}
	

}
