package com.ly.musicplayer.service;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.management.monitor.MonitorSettingException;

import com.ly.musicplayer.util.Message;
import com.ly.musicplayer.util.MessageCenter;
import com.tulskiy.musique.audio.formats.mp3.MP3FileReader;
import com.tulskiy.musique.audio.player.Player;
import com.tulskiy.musique.audio.player.PlayerEvent;
import com.tulskiy.musique.audio.player.PlayerEvent.PlayerEventCode;
import com.tulskiy.musique.audio.player.PlayerListener;
import com.tulskiy.musique.model.Track;
import com.tulskiy.musique.util.AudioMath;

import davaguine.jmac.core.jmacu;


/*
 * 会发送两个消息：
 * 1、音乐播放自动停止；
 * 
 * 2、播放过程中每0.2发送播放进度；
 * 
 * 3、播放暂停UPDATE_PAUSE_STATE
 * 
 */

public class Mp3PlayerService{
	
	private Thread progressUpdate = null; 
	//ObserverManage -- > MessageCenter
	private MessageCenter messageCenter = null;
	private int volume = 50;
	
	private static Mp3PlayerService mp3PlayerService = null;
	public static Mp3PlayerService getMp3PlayerService() {
		if (mp3PlayerService == null) {
			mp3PlayerService = new Mp3PlayerService();
		}
		return mp3PlayerService;
	}
	
	
	private Player mediaPlayer = null;
	private Track track;
	private boolean isManualStop = false;
	private boolean isPause = false;
	
	private  Mp3PlayerService() {
		//observerManage = ObserverManage.getObserver();
		messageCenter = MessageCenter.getMessageCenter();
	}
	
	public void playFile(String filePath) {
		
		if(mediaPlayer == null) {
			mediaPlayer = new Player();
			setVolume(volume);
			isPause = false;
			File songFile = new File(filePath);
			MP3FileReader reader = new MP3FileReader();
			track = reader.read(songFile);
			mediaPlayer.open(track);
			isManualStop = false;
			
			mediaPlayer.addListener(new PlayerListener() {				
				@Override
				public void onEvent(PlayerEvent e) {
					if(e.getEventCode() == PlayerEventCode.STOPPED) {
						
						if(!isManualStop) {
							//如果是自动停止的播放器会通过MessageCenter发送一个消息PLAY_STOP
							Message msg = new Message(Message.PLAY_STOP);							
							messageCenter.sendMessage(msg);
						}
						progressUpdate.stop();
						progressUpdate = null;
						if(mediaPlayer != null) {
							mediaPlayer = null;
						}
					}
				}
			});
			
			
			class ProgressUpdate extends Thread{				
				@Override
				public void run() {					
					while(mediaPlayer != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						long progress = new Double(mediaPlayer.getCurrentMillis()).longValue();
						Message msg = new Message(Message.PLAY_PROGRESS_UPDATE);
						msg.setProgress(progress/1000);
						messageCenter.sendMessage(msg);
					}
				}
			} 
			progressUpdate = new ProgressUpdate();
			progressUpdate.start();
		}
	}
	
	/*
	 * 停止播放
	 * */	
	public void stopPlay() {
		if(mediaPlayer != null) {
			isManualStop = true;
			mediaPlayer.stop();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * 暂停播放
	 * */
	public void pause() {
		if(mediaPlayer != null) {
			isPause = !isPause;
			mediaPlayer.pause();
			
			Message msg = new Message(Message.UPDATE_PAUSE_STATE);
			msg.setPause(isPause);
			messageCenter.sendMessage(msg);
		}
	}
	
	/*
	 * 播放时间的毫秒数
	 * */
	public void seekTo(long millis) {
		if(mediaPlayer != null) {
			long seekByte = AudioMath.millisToSamples(millis, track.getTrackData().getSampleRate());
			mediaPlayer.seek(seekByte);
		}		
	}
	
	
	/*
	 * 设置音量： 0 -- 100
	 * */
    public void setVolume(int volume) {
		if (mediaPlayer != null) {
			this.volume = volume;
		    mediaPlayer.getAudioOutput().setVolume((float)(volume * 1.0/100));
		}
    }
    /*
     *获取当前音量 
     * */
    public int getVolume() {
    	if (mediaPlayer != null) {
		    return (int)(mediaPlayer.getAudioOutput().getVolume(true) * 100);
		}
    	return volume;
    } 
}
