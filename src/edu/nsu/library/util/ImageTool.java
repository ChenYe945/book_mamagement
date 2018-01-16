package edu.nsu.library.util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageTool {
	public  static void setButtonImage(JButton button,String src,
			int width,int height){
		Image img = (new ImageIcon(src)).getImage();
		//将原始图像缩放为宽度为width，高度为height的新图像
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
