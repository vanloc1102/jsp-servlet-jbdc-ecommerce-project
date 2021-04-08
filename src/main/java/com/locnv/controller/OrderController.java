package com.locnv.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.locnv.model.Cart;
import com.locnv.model.CartItem;
import com.locnv.model.User;
import com.locnv.service.CartItemService;
import com.locnv.service.CartService;
import com.locnv.service.UserService;
import com.locnv.service.impl.CartServiceImpl;
import com.locnv.service.impl.CartServiceItemImpl;
import com.locnv.service.impl.UserServiceImpl;
import com.locnv.tools.SendMail;
import com.locnv.util.RandomUUID;

@WebServlet(urlPatterns = "/member/order")
public class OrderController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	CartService cartService = new CartServiceImpl();
	CartItemService cartItemService = new CartServiceItemImpl();
	long time = System.currentTimeMillis();

	@SuppressWarnings("static-access")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		User buyer = (User) obj;
		Cart cart = new Cart();
		cart.setBuyUser(buyer);
		cart.setBuyDate(new java.sql.Date(time));
		cart.setId(RandomUUID.getRandomID());
		cartService.insert(cart);

		Object objCart = session.getAttribute("cart");
		if (objCart != null) {
			// ep ve dung kieu cua no khi them vao o phan them vao gio hang controller
			@SuppressWarnings("unchecked")
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) objCart;

			for (CartItem cartItem : map.values()) {
				cartItem.setCart(cart);
				cartItem.setId(RandomUUID.getRandomID());
				SendMail sm = new SendMail();
				sm.sendMail(cart.getBuyUser().getEmail(), "UNIFY", "Payment success. We will contact you soon ! ");
				cartItemService.insert(cartItem);
				
			}

		}
		session.removeAttribute("cart");
		resp.sendRedirect(req.getContextPath() + "/home");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
