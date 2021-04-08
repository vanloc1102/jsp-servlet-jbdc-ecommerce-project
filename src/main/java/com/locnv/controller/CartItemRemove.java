package com.locnv.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.locnv.model.CartItem;

@WebServlet(urlPatterns = { "/member/cart/remove" }) // ?pId=?123
public class CartItemRemove extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("cart");// Doc tu Session ra
		String pId = req.getParameter("pId");
		if (obj != null) {
			@SuppressWarnings("unchecked")
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) obj; // ep ve kieu cua no
			// Xoa san pham trong map
			map.remove(Integer.parseInt(pId));

			// Cap nhat lai Session
			httpSession.setAttribute("cart", map);
		}
		res.sendRedirect(req.getContextPath() + "/member/cart");
	}
}
