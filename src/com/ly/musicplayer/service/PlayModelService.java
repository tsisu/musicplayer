package com.ly.musicplayer.service;

import com.ly.musicplayer.model.PlayList;
import com.ly.musicplayer.model.SongInfo;

public class PlayModelService {
//单列  一个类只有一个对象实例
	
	 private static PlayModelService playModelService = null ;
	 public static  PlayModelService getPlayModelService() {
		 if (playModelService  == null) {
			playModelService  = new PlayModelService();
		} 
			
	
		 return playModelService;
	 }
		 
		private PlayModelService() {
			
		} 
		public static int MODE_AEQUNENCE = 1;
		public static int MODE_RANDOM = 2;
		public static int MODE_SINGLE = 3;
		private int playMode = MODE_AEQUNENCE;
		public int getPlayModel() {
			return  playMode;
		}
		public  void setPlayMode(int playMode) {
			
			this.playMode = playMode;
		}
		//顺序模式下获取上一首歌曲
		private SongInfo getSqunenceLast() {
				//获取当前播放列表 
				PlayListService playListService = PlayListService.getPlayListService();
				PlayList playList = playListService.getPlayList();
				//播放当前音乐所在位置
				Integer index = getCurrentIndex(playList);
				Integer lastIndex = (index - 1 + playList.getItems().size()) % playList.getItems().size();
				//返回lastIndex
			return playList.getItems().get(lastIndex);
		}
		private int getCurrentIndex(PlayList playList) {
			int i = 0;
			for (SongInfo songInfo : playList.getItems()) {
				if (songInfo.getuId().equals(playList.getCurrentPlay())) {
					break;
				}
				i++;
			}
			return i;
		}
		private SongInfo getSingle() {
			//获取当前播放列表
			PlayListService playListService = PlayListService.getPlayListService();
			PlayList playList = playListService.getPlayList();
			
			//当期正在播放音乐歌曲所在位置
			int index = getCurrentIndex(playList);
		    return playList.getItems().get(index);
			
			
		}
		private SongInfo getSqunenceNext() {
			PlayListService playListService = PlayListService.getPlayListService();
			PlayList playList = playListService.getPlayList();
			
			Integer index = getCurrentIndex(playList);
			Integer nextIndex = (index + 1) % playList.getItems().size();
			return  playList.getItems().get(nextIndex);
			
		}
		/*
		 * private SongInfo getSqunencePre() { return null;
		 * 
		 * 
		 * }
		 */

		public SongInfo getNextSong() {
			// TODO Auto-generated method stub
			return getSqunenceNext();
		}

		public SongInfo getLastSong() {
			// TODO Auto-generated method stub
			return getSqunenceLast();
		}
		public SongInfo getSingleSong() {
			return getSingle();
		}
}
