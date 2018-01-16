package edu.nsu.library.bean;

public class Category {
	private String name;
	private String code;
	private String fathercode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFathercode() {
		return fathercode;
	}
	public void setFathercode(String fathercode) {
		this.fathercode = fathercode;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
