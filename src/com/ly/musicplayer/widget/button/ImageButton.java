package com.ly.musicplayer.widget.button;

import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ImageButton extends JButton {
    public ImageButton(String normalIconPath,String hotIconPath,
    		String pressIconPath,int width,int height) {
    	      super();
		ImageIcon normalIcon = new ImageIcon(normalIconPath);
		normalIcon.setImage(normalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setIcon(normalIcon);
		
		ImageIcon hotIcon = new ImageIcon(hotIconPath);
		hotIcon.setImage(hotIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setRolloverIcon(hotIcon);
		
		
		ImageIcon pressIcon = new ImageIcon(pressIconPath);
		pressIcon.setImage(pressIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setPressedIcon(pressIcon);
		
		//去掉原有图片的一些显示
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setDoubleBuffered(false);
		this.setOpaque(false);
		this.setFocusable(false);
		
		// 设置鼠标的图标
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
