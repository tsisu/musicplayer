package com.ly.musicplayer.widget.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.ly.musicplayer.model.PlayList;
import com.ly.musicplayer.model.SongInfo;
import com.ly.musicplayer.service.PlayListService;
import com.ly.musicplayer.util.Message;
import com.ly.musicplayer.util.MessageCenter;



public class PlayListViewPanel extends JPanel implements Observer {
	private JPanel playListHead;
	private JPanel listContentPanel;
	private int width;
	private int height;
       public PlayListViewPanel(int width,int height) {
    	  super();
    	  this.height=height;
    	  this.width=width;
    	   
    	  this.setLayout(new BorderLayout());
    	  this.setBackground(null);
    	  this.setOpaque(false);
    	  
    	 initComponent(PlayListService.getPlayListService().getPlayList());
    	 MessageCenter.getMessageCenter().addObserver(this);
    	  
    	  
    	
       }
         private void initComponent(PlayList playList) {
    	   this.setBackground(new Color(255,255,255,100));
     	  playListHead = new ListHeadPanel("默认列表",playList.getItems().size(),width);
     	  this.add(playListHead,BorderLayout.NORTH);
     	  
     	  listContentPanel  = new JPanel();
     	  listContentPanel.setLayout(new BoxLayout(listContentPanel, BoxLayout.Y_AXIS));
     	  listContentPanel.setOpaque(false);
     	  this.add(listContentPanel,BorderLayout.CENTER);
     	  
     	  
     	  JScrollPane scrollPane = new JScrollPane();
     	  scrollPane.setViewportView(listContentPanel);
     	  scrollPane.setOpaque(false);
     	  scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
     	  scrollPane.getViewport().setBackground(new Color(255,255,255,100));
     	  updatePlayList(playList);
     	  this.add(scrollPane,BorderLayout.CENTER);
    	   
    	   
       }
       
    //播放歌曲   
	private void updatePlayList(PlayList playList) {
		for(SongInfo songInfo : playList.getItems()) {
			boolean isSelected = songInfo.getuId().equals(playList.getCurrentPlay());
			listContentPanel.add(new listItemPanel(songInfo, isSelected, width, this));
			
		}
		
		
		
		
		// TODO Auto-generated method stub
	/*	for(int i = 0; i < 13;i++)
  	  {
  		  
  		  SongInfo songInfo = new SongInfo();
  		  songInfo.setTitle("刚刚好");
  		  songInfo.setSinger("薛之谦");
  		  boolean isSelect = (i==0);
  		  listContentPanel.add(new listItemPanel(songInfo, false,width, this));
  		  
  	  }*/
	}
	
	public void update(Observable observable,Object data ) {
		
		Message message =(Message)data;
		if(message.getType() == Message.PLAY_LIST_UPDATE) {
			this.setVisible(false);
			this.removeAll();
			initComponent(PlayListService.getPlayListService().getPlayList());
			this.setVisible(true);
			
		}else if (message.getType() == message.CURRENT_PLAY_UPDATE) {
			this.setVisible(false);
			listContentPanel.removeAll();
			updatePlayList(PlayListService.getPlayListService().getPlayList());
			this.setVisible(true);
			
		}
		
		
	}
}
