package edu.nsu.library.bean;

public class Suggest {
	private int id;
	private int userid;
	private String content;
	private String suggestTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSuggestTime() {
		return suggestTime;
	}
	public void setSuggestTime(String suggestTime) {
		this.suggestTime = suggestTime;
	}
	

}
