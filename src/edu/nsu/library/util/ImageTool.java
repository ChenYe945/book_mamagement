package edu.nsu.library.util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageTool {
	public  static void setButtonImage(JButton button,String src,
			int width,int height){
		Image img = (new ImageIcon(src)).getImage();
		//��ԭʼͼ������Ϊ���Ϊwidth���߶�Ϊheight����ͼ��
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(img));
		
	}
	public static void setLabelImage(JLabel label,String src){
		int width = label.getWidth();
		int height = label.getHeight();
		Image img = (new ImageIcon(src)).getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(img));
	}

}
