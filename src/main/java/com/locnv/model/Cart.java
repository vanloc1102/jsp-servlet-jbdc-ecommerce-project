/**
 * 
 */
package com.locnv.model;

import java.sql.Date;

/**
 * @author vanloc
 *
 */
public class Cart {

	private String id;
	private User buyUser;
	private Date buyDate;

	/**
	 * 
	 */
	public Cart() {
		super();
	}

	/**
	 * @param id
	 * @param buyUser
	 * @param buyDate
	 */
	public Cart(String id, User buyUser, Date buyDate) {
		super();
		this.id = id;
		this.buyUser = buyUser;
		this.buyDate = buyDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(User buyUser) {
		this.buyUser = buyUser;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

}
