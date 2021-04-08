package com.locnv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns= {"/member/cart"})
public class CartController extends HttpServlet {
;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession httpSession = req.getSession();
//		Object obj = httpSession.getAttribute("cart");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/view/list-cart.jsp");
		dispatcher.forward(req, res);
	}
}
