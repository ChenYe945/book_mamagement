package edu.nsu.library.bean;

public class Book {
	private int id;
	private String bookname;
	private String author;
	private String press;
	private String presstime;
	private String c3code;
	private float price;;
	private String isbn;
	private int borrowcounts;
	private int bookcounts;
	private int recommend;
	private String introuction;
	private String cover;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPresstime() {
		return presstime;
	}
	public void setPresstime(String presstime) {
		this.presstime = presstime;
	}
	public String getC3code() {
		return c3code;
	}
	public void setC3code(String c3code) {
		this.c3code = c3code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getBorrowcounts() {
		return borrowcounts;
	}
	public void setBorrowcounts(int borrowcounts) {
		this.borrowcounts = borrowcounts;
	}
	public int getBookcounts() {
		return bookcounts;
	}
	public void setBookcounts(int bookcounts) {
		this.bookcounts = bookcounts;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getIntrouction() {
		return introuction;
	}
	public void setIntrouction(String introuction) {
		this.introuction = introuction;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	

}
