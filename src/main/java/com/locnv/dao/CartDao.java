package com.locnv.dao;

import java.util.List;

import com.locnv.model.Cart;

public interface CartDao {
	void insert(Cart cart);

	void edit(Cart cart);

	void delete(int id);

	Cart get(String name);

	Cart get(int id);

	List<Cart> getAll();

	List<Cart> search(String name);
}
