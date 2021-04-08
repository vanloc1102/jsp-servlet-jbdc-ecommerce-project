package com.locnv.service.impl;

import java.util.List;

import com.locnv.dao.CartDao;
import com.locnv.dao.impl.CartDaoImpl;
import com.locnv.model.Cart;
import com.locnv.service.CartService;

public class CartServiceImpl implements CartService {
	CartDao cartDao = new CartDaoImpl();

	@Override
	public List<Cart> search(String name) {
		return cartDao.search(name);
	}

	@Override
	public void insert(Cart cart) {
		cartDao.insert(cart);

	}

	@Override
	public List<Cart> getAll() {
		return cartDao.getAll();
	}

	@Override
	public Cart get(int id) {
		return cartDao.get(id);
	}

	@Override
	public void edit(Cart newCart) {
		Cart oldCart = cartDao.get(newCart.getId());

		oldCart.setBuyDate(newCart.getBuyDate());
		oldCart.setBuyUser(newCart.getBuyUser());
		
		
		cartDao.edit(oldCart);

	}

	@Override
	public void delete(int id) {
		cartDao.delete(id);
	}
}
