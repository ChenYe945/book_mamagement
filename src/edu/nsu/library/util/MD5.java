package edu.nsu.library.util;

import java.security.MessageDigest;

public class MD5 {
	public static String get(String str){
		StringBuffer sb = new StringBuffer();
		try {//获得MD5加密算法的信息
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());//将原始字符串转为字节数组并调用update方法
			byte[] b = md.digest();//获取原始字符串的摘要信息
			int n = 0;
			for(int i=0;i<b.length;i++){
				n=b[i];
				if(n<0){
					n+=256;
				}
				
				//如果小于16，前面补0，是每个数都用2位16进制整数表示
				if(n<16)
					sb.append("0");
				sb.append(Integer.toHexString(n));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sb.toString();
		
	}
	public static void main(String args[]){
		System.out.println(get("123"));
	}

}
