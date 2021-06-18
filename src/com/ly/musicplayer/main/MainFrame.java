package com.ly.musicplayer.main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.management.loading.PrivateClassLoader;
import javax.swing.JFrame;
import com.ly.musicplayer.util.Contants;
import com.ly.musicplayer.widget.panel.MainPanel;


import java.awt.Point ;
import java.awt.Rectangle;




 class MainFrameMouserListenser extends MouseAdapter{
	 private Point lastPoint;
	 private JFrame window;
	 public MainFrameMouserListenser(JFrame window) {
		// TODO Auto-generated constructor stub
		 this.window = window;
	}
	 
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//按下时记录一个当前鼠标的点的位置
			lastPoint = e.getLocationOnScreen();
		}
		
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//计算前一个位置的偏移量
			Point point =e.getLocationOnScreen();
			int offsetX = point.x - lastPoint.x;
			int offsetY = point.y - lastPoint.y;
			
		     Rectangle bounds =window.getBounds();
		     bounds.x += offsetX;
		     bounds.y += offsetY;
		     
		     
		     window.setBounds(bounds);
		     
		     lastPoint = point;
			
			
		}
 }

/*
	class MainFrameMouserListenser implements MouseListener,
	       MouseMotionListener{
		private  Point lastPoint;
		private JFrame window;
	
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//按下时记录一个当前鼠标的点的位置
		}



		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			//计算前一个位置的偏移量
		}



		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
*/



public class MainFrame extends JFrame {
 private static final long serialVersionUID = 1L;
 
    public MainFrame() {
		// TODO Auto-generated constructor stub
    	super();
    	
    	this.setSize( Contants.FRAME_WIDTH,Contants.FRAME_HEIHT);
    	this.setLocation(100, 100);
    	
    	
    	this.setResizable(false);
    	
    	
    	MainPanel mainPanel = new MainPanel();
    	this.add(mainPanel);
    	this.setUndecorated(true);
    	
    	this.setOpacity(1f);
    	MainFrameMouserListenser mouserListenser = new MainFrameMouserListenser(this);
    	this.addMouseListener(mouserListenser);
    	this.addMouseMotionListener(mouserListenser);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
}
