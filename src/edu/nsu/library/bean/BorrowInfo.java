package edu.nsu.library.bean;

import javax.xml.crypto.Data;

public class BorrowInfo {
	private int id;
	private int bookId;
	private int userId;
	private int renew;
	private String borrowTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRenew() {
		return renew;
	}
	public void setRenew(int renew) {
		this.renew = renew;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

}
