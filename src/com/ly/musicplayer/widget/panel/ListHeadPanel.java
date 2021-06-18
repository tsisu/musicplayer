package com.ly.musicplayer.widget.panel;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import com.ly.musicplayer.service.PlayListService;
import com.ly.musicplayer.util.Contants;
import com.ly.musicplayer.widget.button.ImageButton;



class Mp3Filter extends FileFilter{
	@Override
	public boolean accept(File file) {
		String name = file.getName();
		return file.isDirectory() 
				|| name.toLowerCase().endsWith(".MP3") 
				|| name.toLowerCase().endsWith(".mp3");
	}
	@Override
	public String getDescription() {
		return "*.mp3";
	}
	
}
public class ListHeadPanel extends JPanel {
	
	private JLabel listNameLable;
	private JButton addListButton;
    public ListHeadPanel(String listName ,int iCount ,int width) {
    	super();
    	
        this.setPreferredSize(new Dimension(0,Contants.PLAYLIST_HEAD_HEIGHT));
    	this.setBackground(new Color(255, 255, 255, 150));
    	
    	listNameLable = new JLabel(listName +"["+ iCount +"]");
    	listNameLable.setBounds(0, 0, 150,Contants.PLAYLIST_HEAD_HEIGHT );
    	listNameLable.setForeground(Color.BLACK);
    	listNameLable.setFont(new Font("微软雅黑",Font.PLAIN,18));
    	
    	this.add(listNameLable);
    	
    	addListButton = new ImageButton("musicplayer//icon//list_btn_def.png", 
    			"musicplayer//icon//list_btn_rollover.png",
    			"musicplayer//icon//list_btn_pressed.png", 35, 35);
    	
    	
    	addListButton.setBounds(width-Contants.PLAYLIST_HEAD_HEIGHT, (Contants.PLAYLIST_HEAD_HEIGHT - 30)/2,
    		    -20, 0);
    	addListButton.setToolTipText("添加歌曲");
    	this.add(addListButton);
    	
    	addListButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setFileSelectionMode(jFileChooser.FILES_ONLY);
				jFileChooser.setMultiSelectionEnabled(true);
				jFileChooser.setFileFilter(new Mp3Filter());
				jFileChooser.showDialog(new JLabel(), "选择音乐");
				
				File[] files =jFileChooser.getSelectedFiles();
				jFileChooser.showDialog(new JLabel(), "选择音乐");
				PlayListService playListService = PlayListService.getPlayListService();
				playListService.addPlaylist(files);
			}
		});
    	
    }
}
