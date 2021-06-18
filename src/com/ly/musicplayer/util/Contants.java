package com.ly.musicplayer.util;

public class Contants {
     public static final int FRAME_WIDTH = 950;
     public static final int FRAME_HEIHT = 700;
     public static final int TITLE_HEIGHT = 80;
     public static final int OPERATE_PANEL_HEIGHT = 80;
     public static final int PLAYLST_PANEL_WIDTH = 400;
     public static final int TITLE_PANEL_HEIGHT = 85;
	 public static final int CLOSE_BUTTON_WIDTH = 25;
	
	 public static final int PLAYLIST_VIEW_WIDTH =PLAYLST_PANEL_WIDTH -30;
	 public static final int PLAYLIST_HEAD_HEIGHT =45; 
	 public static final int SONGNAME_FONT = 70;
	 public static final int PLAY_BUTTON_WIDTH = 70;
	 public static final int VOLUME_BUTTON_WIDTH = 40;
	 public static final int PROGRESS_BAR_WIDTH = 500;
	 public static final int PLAY_BUTTON_HEIGHT = 50;
	 public static final int PLAYSOUND_BUTTON_WIDTH = 40;
	    public static final int PLAYSOUND_BUTTON_HEIGTH = 40;
	 
	 
	public static String formatTime(long time) {
		// TODO Auto-generated method stub
		long minute = time/60;
		long sceond = time % 60;
		minute %= 60;
		return String.format("%02d:%02d", minute,sceond);
	}
	 
	 
}
