package com.locnv.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locnv.dao.CartDao;
import com.locnv.jbdc.JDBCConnection;
import com.locnv.model.Cart;
import com.locnv.model.User;
import com.locnv.service.UserService;
import com.locnv.service.impl.UserServiceImpl;

public class CartDaoImpl extends JDBCConnection implements CartDao {
	UserService userService = new UserServiceImpl();

	@Override
	public void insert(Cart cart) {
		String sql = "INSERT INTO cart(id,user_id, buyDate) VALUES (?,?,?)";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cart.getId());
			ps.setInt(2, cart.getBuyUser().getId());
			ps.setDate(3, new Date(cart.getBuyDate().getTime()));
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Cart cart) {
		String sql = "UPDATE cart SET user_id = ?, buyDate = ? WHERE id = ?";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, cart.getBuyUser().getId());
			ps.setDate(2, new Date(cart.getBuyDate().getTime()));
			ps.setString(3, cart.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM cart WHERE id = ?";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Cart get(int id) {
		String sql = "SELECT cart.id, cart.buyDate, user.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.user_id = user.id WHERE cart.id=?";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyUser(user);;

				return cart;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> getAll() {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, user.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.user_id = user.id";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyUser(user);;

				cartList.add(cart);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	public List<Cart> search(String name) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, user.email, user.username, user.id AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.user_id = user.id LIKE user.email = ?";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userService.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyUser(user);;

				cartList.add(cart);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public Cart get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
