package com.ly.musicplayer.service;

import java.io.File;


import java.util.LinkedList;

import javax.print.attribute.standard.PrinterLocation;

import com.ly.musicplayer.model.PlayList;
import com.ly.musicplayer.model.SongInfo;
import com.ly.musicplayer.util.AudioFileReader;
import com.ly.musicplayer.util.Message;
import com.ly.musicplayer.util.MessageCenter;


public class PlayListService {
	private static PlayListService playListService =null;
	
	public static PlayListService getPlayListService() {
		if (playListService == null ) {
			playListService = new PlayListService();
		}
		return playListService;
	}
	
 private PlayList playList;
 
 
 public PlayListService() {
	// playList.setName("默认列表");
	 playList  = new PlayList();
	 playList.setCurrentPlay(null);
	 playList.setItems(new LinkedList<>());
 }
 
 public void addPlaylist(File[]files ) {
	 for(File file :files) {
		 AudioFileReader reader = new AudioFileReader();
		 SongInfo songInfo = reader.readeAudioFile(file);
		 if (songInfo != null) {
			playList.getItems().add(songInfo);
		}
		 }
	 //完成playlist的更新
	 MessageCenter messageCenter = MessageCenter.getMessageCenter();
	 messageCenter.sendMessage(new Message(Message.PLAY_LIST_UPDATE));
	     // System.out.println(playList.getItems());
	 
 }
 
   public PlayList getPlayList() {
	   return playList;
   }
   
   
  public void setCurrentPlay(SongInfo songInfo) {
	  System.out.println(songInfo);
	  playList.setCurrentPlay(songInfo.getuId());
	  Mp3PlayerService mp3PlayerService = Mp3PlayerService.getMp3PlayerService();
	  mp3PlayerService.stopPlay();
	  mp3PlayerService.playFile(songInfo.getFilePath());
	  //发送通知当前播放的音乐更新
	  MessageCenter messageCenter = MessageCenter.getMessageCenter();
	  Message message = new Message(Message.CURRENT_PLAY_UPDATE);
	  message.setSongInfo(songInfo);
	  messageCenter.sendMessage(message);
	  
  } 
}
