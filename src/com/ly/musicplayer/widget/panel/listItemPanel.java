package com.ly.musicplayer.widget.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ly.musicplayer.model.SongInfo;
import com.ly.musicplayer.service.PlayListService;
import com.ly.musicplayer.util.Contants;
import com.ly.musicplayer.util.Message;
import com.ly.musicplayer.util.MessageCenter;

public  class listItemPanel extends JPanel implements Observer {
	private int width;
	private int height = 40;
	private JLabel songNameLable;
	private Color nomarlBackground ;
	private Color unselectedNomarlBackground = new Color(255,255,255,80);
	private Color selectedNomarlBackground = new Color(255,255,255,80);
	private Color rolloverBackground = new Color(255,255,255,120);
	private JPanel listViewPanel;
	private boolean isSelected;
	
	private  SongInfo songInfo;
    public listItemPanel(SongInfo songInfo ,boolean isSelected, int width,JPanel
    		listViewPanel) {
    	
    	super();
    	this.songInfo  = songInfo;
    	this.width = width;
    	height = isSelected ? 80 : 40;
    	
    	nomarlBackground = isSelected ? selectedNomarlBackground :unselectedNomarlBackground;
      this.setPreferredSize(new Dimension(width,height));	
      this.setMaximumSize(new Dimension(width,height ));
      this.setMinimumSize(new Dimension(width,height ));
      this.setBackground(nomarlBackground);
      this.setLayout(null);
      this.listViewPanel= listViewPanel; 
      class ListItemMouselistener extends MouseAdapter{
    	  @Override
    	public void mouseExited(MouseEvent e) {
    		
    		  setBackground(nomarlBackground);
    		  listViewPanel.repaint();
    	}
    	  @Override
    	public void mouseEntered(MouseEvent e) {
    		
    		  setBackground(rolloverBackground);
    		  listViewPanel.repaint();
    	}
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		// TODO Auto-generated method stub
    		if (e.getClickCount() == 2 && !isSelected) {
				PlayListService.getPlayListService().setCurrentPlay(songInfo);
			}
    	} 
    	
      }
    	this.addMouseListener(new ListItemMouselistener());
    	if(isSelected) 
    	{
    		initSeletedComponent();
    	}else
    	{
    		initUnselectedComponent();
    	}
      
     MessageCenter.getMessageCenter().addObserver(this);
    }
	private void initUnselectedComponent() {
		// TODO Auto-generated method stub
		  songNameLable = new JLabel(songInfo.getSinger() + ":" + songInfo.getTitle());
		  songNameLable.setBounds(5, 0, width, height);
		  songNameLable.setFont(new Font("微软雅黑",Font.PLAIN,17));
		  this.add(songNameLable);
	}
	private String BASE_PATH = "musicplayer//icon//";
	JLabel singerIconLable;
	JLabel songProgress;
	
	
	private void initSeletedComponent() {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon("musicplayer//icon//trayIcon.png");
		Image singer  = songInfo.getSingerPIC()==null ?  icon.getImage() : songInfo.getSingerPIC();
		icon.setImage(singer
				.getScaledInstance(height*3/4, height*3/4, Image.SCALE_SMOOTH));
		singerIconLable = new JLabel();
		singerIconLable.setIcon(icon);
		singerIconLable.setBounds(height/8, height/8, height*3/4, height*3/4);
		
		String title = songInfo.getSinger()+":"+songInfo.getTitle();
		songNameLable = new JLabel(title);
		songNameLable.setFont(new Font("微软雅黑",Font.PLAIN,18));
		songNameLable.setBounds(height, 0, 200, height/2);
		
		String progress = "00:00/"+Contants.formatTime(songInfo.getDuration());
		songProgress = new JLabel(progress);
		songProgress.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		songProgress.setBounds(height, height/2, 200, height/2);
		
		this.add(singerIconLable);
		this.add(songNameLable);
		this.add(songProgress);
		this.add(songNameLable);
	}
	@Override
	public void update(Observable o, Object data) {
		// TODO Auto-generated method stub
		Message message = (Message)data;
		if (message.getType() == Message.PLAY_PROGRESS_UPDATE && isSelected) {
			String progress = Contants.formatTime(message.getProgress()) +"/" 
					 + Contants.formatTime(songInfo.getDuration());
			songProgress.setText(progress);
           listViewPanel.repaint();			
		}
	}
}
