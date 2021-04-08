package com.locnv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locnv.dao.CartItemDao;
import com.locnv.dao.UserDao;
import com.locnv.jbdc.JDBCConnection;
import com.locnv.model.Cart;
import com.locnv.model.CartItem;
import com.locnv.model.Product;
import com.locnv.model.User;
import com.locnv.service.CartService;
import com.locnv.service.ProductService;
import com.locnv.service.impl.CartServiceImpl;
import com.locnv.service.impl.ProductServiceImpl;

public class CartItemDaoImpl extends JDBCConnection implements CartItemDao {
	CartService cartService = new CartServiceImpl();
	ProductService productService = new ProductServiceImpl();
	UserDao userDao = new UserDaoImpl();
	
	
	@Override
	public void insert(CartItem cartItem) {
		String sql = "INSERT INTO cartitem(id, cart_id, product_id, quantity, unit_price) VALUES (?,?,?,?,?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cartItem.getId());
			ps.setString(2, cartItem.getCart().getId());
			ps.setInt(3, cartItem.getProduct().getId());
			ps.setInt(4, cartItem.getQuantity());
			ps.setLong(5, cartItem.getUnitPrice());

			ps.executeUpdate();

//			ResultSet generatedKeys = ps.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				int id = generatedKeys.getInt(1);
//				cartItem.setId(id);
//			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(CartItem cartItem) {
		String sql = "UPDATE cartitem SET cart_id = ?, product_id = ?, quantity = ?, unit_price=? WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, cartItem.getCart().getId());
			ps.setInt(2, cartItem.getProduct().getId());
			ps.setInt(3, cartItem.getQuantity());
			ps.setLong(4, cartItem.getUnitPrice());
			ps.setString(5, cartItem.getId());
			
			
			ps.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM cartitem WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CartItem get(int id) {
		String sql = "SELECT " + 
				"cartitem.id, " + 
				"cartitem.quantity, " + 
				"cartitem.unit_price, " + 
				"cart.user_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM cartitem " + 
				"INNER JOIN cart " + 
				"ON cartitem.cart_id = cart.id " + 
				"INNER JOIN product " + 
				"ON cartitem.product_id = product.id " +
				"WHERE cartitem.id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("user_id"));
				
				Cart cart = new Cart();
				cart.setBuyUser(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unit_price"));
				
				
				return cartItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartItem> getAll() {
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		String sql = "SELECT " + 
				"cartitem.id, " + 
				"cartitem.quantity, " + 
				"cartitem.unit_price, " + 
				"cart.user_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM cartitem " + 
				"INNER JOIN cart " + 
				"ON cartitem.cart_id = cart.id " + 
				"INNER JOIN product " + 
				"ON cartitem.product_id = product.id ";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("user_id"));
				
				Cart cart = new Cart();
				cart.setBuyUser(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setId(rs.getString("id"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unit_price"));

				cartItemList.add(cartItem);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItemList;
	}

	public List<CartItem> search(String name) {
		return null;
	}

	@Override
	public CartItem get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
