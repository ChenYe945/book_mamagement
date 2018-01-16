package edu.nsu.library.util;

import java.security.MessageDigest;

public class MD5 {
	public static String get(String str){
		StringBuffer sb = new StringBuffer();
		try {//���MD5�����㷨����Ϣ
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());//��ԭʼ�ַ���תΪ�ֽ����鲢����update����
			byte[] b = md.digest();//��ȡԭʼ�ַ�����ժҪ��Ϣ
			int n = 0;
			for(int i=0;i<b.length;i++){
				n=b[i];
				if(n<0){
					n+=256;
				}
				
				//���С��16��ǰ�油0����ÿ��������2λ16����������ʾ
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
