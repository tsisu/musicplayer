package com.ly.musicplayer.widget.panel;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;




public class MainPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	private JPanel titlePanel;
	private JPanel playListPanel;
	private JPanel operatePanel;
	private JPanel lrcPanel;
	
	public MainPanel() {
		// TODO Auto-generated constructor stub
		super();
		
		//创建并添加布局的四个Panel
		this.setLayout(new BorderLayout());
		titlePanel = new TitlePanel();
		playListPanel =new PlayListPanel();
		operatePanel = new OperatePanel();
		lrcPanel =new LRCPanel();
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(playListPanel,BorderLayout.WEST);
		this.add(operatePanel,BorderLayout.SOUTH);
		this.add(lrcPanel,BorderLayout.CENTER);
		
	}
     @Override
	public void paintComponent(Graphics g) {
		Image image = new  ImageIcon("musicplayer\\background\\1.jpg").getImage();
		
		g.drawImage(image,0,0,
				   this.getWidth(),this.getHeight(),this);
		
		
		
	}
}
