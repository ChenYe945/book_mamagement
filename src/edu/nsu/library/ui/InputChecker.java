package edu.nsu.library.ui;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class InputChecker {
	//判断字符串是否为合法的价格
	public static boolean isPrice(String price){
		//\d表示数字,?表示零个或一个
		String reg = "\\d{1,6}.?\\d{0,2}";
		Pattern p = Pattern.compile(reg);
		return p.matcher(price).matches();
		
	}
	public static boolean isDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);//排除非法日期
		boolean flag = true;
		try {
			sdf.parse(date);
		} catch (Exception e) {
			flag = false;
			// TODO: handle exception
		}
		return flag;
		
	}
	public static boolean isPicture(String path){
		String[] exts = {"jpg","png","jpeg","jif","bmp"};
		if(path==null||path.equals("")){
			return false;
		}
		int pos = path.lastIndexOf(".");
		if(pos<0)
			return false;
		//获得文件扩展名
		String ext = path.substring(pos+1);
		ext = ext.toLowerCase();
		for(String s:exts)
			if(ext.equals(s))
				return true;
		return false;
	}

}
