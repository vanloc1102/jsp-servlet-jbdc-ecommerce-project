package com.locnv.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.locnv.service.CartItemService;
import com.locnv.service.impl.CartServiceItemImpl;
@WebServlet(urlPatterns="/admin/order/delete")
public class OrderDeleteController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CartItemService cartItemService=new CartServiceItemImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		cartItemService.delete(id);
		resp.sendRedirect(req.getContextPath()+"/admin/order/list");

	}

}
