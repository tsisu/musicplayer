package com.ly.musicplayer.widget.panel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.ly.musicplayer.util.Contants;

public class PlayListPanel extends JPanel {
	private JPanel playListView;
     public PlayListPanel() {
    	 super();
    	 this.setPreferredSize(new Dimension(Contants.PLAYLST_PANEL_WIDTH,0));
    	 this.setBackground(null);
    	 this.setOpaque(false);
    	 this.setLayout(null);
    	 
    	 playListView = new PlayListViewPanel( Contants.PLAYLIST_VIEW_WIDTH, 
    			 Contants.FRAME_HEIHT - Contants.TITLE_PANEL_HEIGHT - Contants.OPERATE_PANEL_HEIGHT);
    	 playListView.setBounds(Contants.PLAYLST_PANEL_WIDTH - Contants.PLAYLIST_VIEW_WIDTH, 0, 
    			 Contants.PLAYLIST_VIEW_WIDTH,
    			 Contants.FRAME_HEIHT - Contants.TITLE_PANEL_HEIGHT - Contants.OPERATE_PANEL_HEIGHT);
    	 this.add(playListView);
     }
}
