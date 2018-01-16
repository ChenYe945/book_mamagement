package edu.nsu.library.ui;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class InputChecker {
	//�ж��ַ����Ƿ�Ϊ�Ϸ��ļ۸�
	public static boolean isPrice(String price){
		//\d��ʾ����,?��ʾ�����һ��
		String reg = "\\d{1,6}.?\\d{0,2}";
		Pattern p = Pattern.compile(reg);
		return p.matcher(price).matches();
		
	}
	public static boolean isDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);//�ų��Ƿ�����
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
		//����ļ���չ��
		String ext = path.substring(pos+1);
		ext = ext.toLowerCase();
		for(String s:exts)
			if(ext.equals(s))
				return true;
		return false;
	}

}
