package com.ly.musicplayer.model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private String name;
	private String currentPlay;
	private List<SongInfo> items = new ArrayList<>();
	
	public String getCurrentPlay() {
		return currentPlay;
	}
	public void setCurrentPlay(String currentPlay) {
		this.currentPlay = currentPlay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SongInfo> getItems() {
		return items;
	}
	public void setItems(List<SongInfo> items) {
		this.items = items;
	}	
}
