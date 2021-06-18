package com.ly.musicplayer.util;

import com.ly.musicplayer.model.SongInfo;

public class Message {
    public static final int PLAY_LIST_UPDATE=1000;
    public static final int CURRENT_PLAY_UPDATE = 1001;
    public static final int PLAY_STOP = 1002;
    public static final int PLAY_PROGRESS_UPDATE = 1003;
    public static final int UPDATE_PAUSE_STATE = 1004;
    
    
    
    
    private int type;
    
     public Message(int type) {
    	 this.type = type;
    	 
     }
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
	private SongInfo songInfo;

	public SongInfo getSongInfo() {
		return songInfo;
	}
	public void setSongInfo(SongInfo songInfo) {
		this.songInfo = songInfo;
	}
	private long progress;
	
   public long getProgress() {
		return progress;
	}
	public void setProgress(long progress) {
		this.progress = progress;
	}

private boolean isPause = false;

public boolean isPause() {
	return isPause;
}
public void setPause(boolean isPause) {
	this.isPause = isPause;
}
   
	
}
