package com.ly.musicplayer.model;

import java.awt.Image;

public class SongInfo {
	private String uId = "";
    private String title = ""; // 歌曲名称
    private String singer = ""; // 歌手名称
    private long duration; // 歌曲时长
    private long size; // 文件大小
    private String filePath = "";// 文件路径
    private Image singerPIC = null;// 歌手写真图片下载路径
    
    private long playProgress;// 播放的进度
    
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSinger() {
		return singer;
	}


	public void setSinger(String singer) {
		this.singer = singer;
	}


	public long getDuration() {
		return duration;
	}


	public void setDuration(long duration) {
		this.duration = duration;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public Image getSingerPIC() {
		return singerPIC;
	}


	public void setSingerPIC(Image singerPIC) {
		this.singerPIC = singerPIC;
	}


	public long getPlayProgress() {
		return playProgress;
	}


	public void setPlayProgress(long playProgress) {
		this.playProgress = playProgress;
	}


	public String getuId() {
		return uId;
	}


	public void setuId(String uId) {
		this.uId = uId;
	}


	@Override
	public String toString() {
		return "SongInfo [uId=" + uId + ", title=" + title + ", singer=" + singer + ", duration=" + duration + ", size="
				+ size + ", filePath=" + filePath + ", singerPIC=" + singerPIC + ", playProgress=" + playProgress + "]";
	}
}
