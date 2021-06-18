package com.ly.musicplayer.widget.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ly.musicplayer.util.Contants;
import com.ly.musicplayer.widget.button.ImageButton;



public class TitlePanel extends JPanel {
	private JLabel logoLabel;
	private JButton closeButton;
	private JButton miniButton;
     public TitlePanel() {
    	 
    	 super();
    	 this.setPreferredSize(new Dimension(0,Contants.TITLE_PANEL_HEIGHT));
    	 this.setBackground(null);
    	 this.setOpaque(false);
    	 this.setLayout(null);
    	 
    	 logoLabel = new JLabel(new ImageIcon("musicplayer\\icon\\logo2.png"));
    	 logoLabel.setBounds(0, 0, 150, Contants.TITLE_PANEL_HEIGHT);
    	 this.add(logoLabel);
    	 
    	 
    	// JButton jButton = new JButton();
    	// jButton.setIcon(defaulttIcon);
    	// jButton.setRolloverIcon(rolloverIcon);
    	// jButton.
    	 
    	closeButton = new ImageButton("musicplayer\\icon\\close_normal.png", "musicplayer\\icon\\close_hot.png",
    			"musicplayer\\icon\\close_down.png",Contants.CLOSE_BUTTON_WIDTH, Contants.CLOSE_BUTTON_WIDTH);
    	 closeButton.setBounds(Contants.FRAME_WIDTH - Contants.CLOSE_BUTTON_WIDTH - 5,5,
    			 Contants.CLOSE_BUTTON_WIDTH,Contants.CLOSE_BUTTON_WIDTH);
    	 closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			 System.exit(0);
			}
		});
    	 this.add(closeButton);
    	 
    	 //最小化按钮
    	 miniButton = new ImageButton("musicplayer\\icon\\min_def.png", "musicplayer\\icon\\min_hot.png", 
    			 "musicplayer\\icon\\min_down.png", 
    			 Contants.CLOSE_BUTTON_WIDTH, Contants.CLOSE_BUTTON_WIDTH);
    	 miniButton.setBounds(Contants.FRAME_WIDTH-(Contants.CLOSE_BUTTON_WIDTH + 5)*2,
    			 5, Contants.CLOSE_BUTTON_WIDTH, Contants.CLOSE_BUTTON_WIDTH);
    	 
     miniButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFrame frame =(JFrame)getParent().getParent()
					.getParent().getParent().getParent();
			//JFrame最小化
			frame.setExtendedState(JFrame.ICONIFIED);
		}
	});
     this.add(miniButton); 
}
}

