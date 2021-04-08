package com.locnv.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locnv.model.CartItem;
import com.locnv.service.CartItemService;
import com.locnv.service.CartService;
import com.locnv.service.impl.CartServiceImpl;
import com.locnv.service.impl.CartServiceItemImpl;
@WebServlet(urlPatterns= {"/admin/order/list"})
public class OrderListController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CartItemService cartItemService=new CartServiceItemImpl();
	CartService cartService=new CartServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<CartItem> listCartItem =cartItemService.getAll();
	req.setAttribute("listCartItem", listCartItem);
	RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-order.jsp");
	dispatcher.forward(req, resp);
	}
	
}
