/**
 * 
 */
package com.locnv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locnv.dao.UserDao;
import com.locnv.jbdc.JDBCConnection;
import com.locnv.model.User;

/**
 * @author vanlo
 *
 */
public class UserDaoImpl extends JDBCConnection implements UserDao {

	@Override
	public void insert(User user) {
		int roleId = 0;
		String sql = "INSERT INTO user (email, username, password,avatar,role_id) VALUES (?,?,?,?,?)";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatar());
			try {
				if (user.getRoleId() == 1) {
					roleId = 1;
				} else {
					roleId = 2;
				}

			} catch (Exception e) {
				roleId = 2;
			}
			ps.setInt(5, roleId);
			;
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(User user) {
		String sql = "UPDATE user SET email = ? , username = ?, password = ?, avatar = ?, role_id = ? WHERE id = ?";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAvatar());
			ps.setInt(5, user.getRoleId());
			ps.setInt(6, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
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
	public User get(String username) {
		String sql = "SELECT * FROM user WHERE username = ? ";
		Connection connection = super.getJDBCConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				return user;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User get(int id) {
		String sql = "SELECT * FROM user WHERE id = ? ";
		Connection connection = super.getJDBCConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				return user;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public List<User> search(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM user WHERE name LIKE ? ";
		Connection connection = super.getJDBCConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + username + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		Connection connection = JDBCConnection.getJDBCConnection();
		try {
			String query = "select * from user where email = ?";

			PreparedStatement psmt = connection.prepareStatement(query);

			psmt.setString(1, email);

			ResultSet resultSet = psmt.executeQuery();

			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
			connection.close();
		} catch (SQLException ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		Connection connection = JDBCConnection.getJDBCConnection();
		try {
			String query = "select * from user where username = ?";

			PreparedStatement psmt = connection.prepareStatement(query);

			psmt.setString(1, username);

			ResultSet resultSet = psmt.executeQuery();

			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
			connection.close();
		} catch (SQLException ex) {
		}
		return duplicate;
	}

}
