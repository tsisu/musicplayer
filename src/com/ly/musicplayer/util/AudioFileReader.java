package com.ly.musicplayer.util;
import com.ly.musicplayer.model.SongInfo;


import java.awt.Image;
import java.io.File;
import java.util.UUID;

import javax.swing.ImageIcon;


import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import com.ly.musicplayer.model.SongInfo;


public class AudioFileReader {
	public SongInfo readeAudioFile(File file) {
		SongInfo songInfo = null;
		try {
			MP3File mp3File = new MP3File(file);
			
			long size = file.length();
			
			AbstractID3v2Tag tags = mp3File.getID3v2Tag();		
			MP3AudioHeader header = mp3File.getMP3AudioHeader(); // mp3文件头部信息
		    int length = header.getTrackLength();
		    
			//专辑图片
			AbstractID3v2Frame frame = (AbstractID3v2Frame) tags.getFrame("APIC");
			Image image = null;
			if (frame != null) {
	            FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
	            image = new ImageIcon(body.getImageData()).getImage();
			}
            
			songInfo = new SongInfo();
			String id = UUID.randomUUID().toString(); 
			songInfo.setuId(id);
			songInfo.setSingerPIC(image);
			songInfo.setSinger(tags.getFirst(FieldKey.ARTIST));
			songInfo.setTitle(tags.getFirst(FieldKey.TITLE));
			songInfo.setDuration(length);
			songInfo.setFilePath(file.getPath());
			songInfo.setPlayProgress(0);
			songInfo.setSize(size);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return songInfo;
	}
}
