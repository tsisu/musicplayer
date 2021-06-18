 package com.ly.musicplayer.widget.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ly.musicplayer.model.SongInfo;
import com.ly.musicplayer.service.Mp3PlayerService;
import com.ly.musicplayer.service.PlayListService;
import com.ly.musicplayer.service.PlayModelService;
import com.ly.musicplayer.util.Contants;
import com.ly.musicplayer.util.Message;
import com.ly.musicplayer.util.MessageCenter;
import com.ly.musicplayer.widget.button.ImageButton;
import com.ly.musicplayer.widget.slider.BaseSlider;

public class OperatePanel extends JPanel implements Observer {
	private JSlider progressSilder;
	private JButton lastplayButton;
	private JButton playButton;
	private JButton nextplayButton;
	private JButton lrcButton;
	private JLabel durationLabel;
	private JLabel progressLabel;
	private JButton modeButton;
	private JSlider musicsound;
	private JButton musicsoundButton;
	private Boolean isSeeking = false;//拖拽设置
	private String BASE_PATH = "musicplayer\\icon\\";

	public OperatePanel() {
		super();
		this.setPreferredSize(new Dimension(0, Contants.OPERATE_PANEL_HEIGHT));
		this.setBackground(null);// 设置背景色为空值
		this.setOpaque(false);//设置透明与否，true为不透明，false为透明
		this.setLayout(null);
		init();
		MessageCenter.getMessageCenter().addObserver(this);// 观察者模式
	}

	private void init() {
		// 上一曲按钮
		lastplayButton = new ImageButton(BASE_PATH + "pre_def.png", BASE_PATH + "pre_pressed.png",
				BASE_PATH + "pre_rollover.png", Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		lastplayButton.setBounds(10, Contants.PLAY_BUTTON_HEIGHT - 35, Contants.PLAY_BUTTON_HEIGHT,
				Contants.PLAY_BUTTON_HEIGHT);
		this.add(lastplayButton);
		lastplayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SongInfo songInfo = PlayModelService.getPlayModelService().getLastSong();
				PlayListService.getPlayListService().setCurrentPlay(songInfo);
			}
		});
		// 播放按钮显示
		pudateplayButton(true);
		// 下一曲按钮
		nextplayButton = new ImageButton(BASE_PATH + "next_def.png", BASE_PATH + "next_pressed.png",
				BASE_PATH + "next_rollover.png", Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		nextplayButton.setBounds(10 + (Contants.PLAY_BUTTON_HEIGHT) * 2, (Contants.PLAY_BUTTON_HEIGHT) - 35,
				Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		this.add(nextplayButton);
		nextplayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SongInfo songInfo = PlayModelService.getPlayModelService().getNextSong();
				PlayListService.getPlayListService().setCurrentPlay(songInfo);
			}
		});
		// 歌词按钮显示及其设置
		lrcButton = new ImageButton("musicplayer\\icon\\deslrc_def.png", "musicplayer\\icon\\deslrc_hot.png",
				"musicplayer\\icon\\deslrc_pressed.png", 40, 40);
		lrcButton.setBounds((Contants.PLAY_BUTTON_HEIGHT) * 18 - 20, Contants.PLAY_BUTTON_HEIGHT - 35,
				Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		this.add(lrcButton);
		//播放模式显示及其设置
		modeButton = new ImageButton(BASE_PATH + "play_model\\01_5.png", BASE_PATH + "play_model\\01_5.png",
				BASE_PATH + "play_model\\01_5.png", 40, 40);
		modeButton.setBounds((Contants.PLAY_BUTTON_HEIGHT) * 19, Contants.PLAY_BUTTON_HEIGHT - 30,
				Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		this.add(modeButton);
		//播放进度条显示位置及其设置
		progressSilder = new BaseSlider();
		progressSilder.setOpaque(false);
		progressSilder.setFocusable(false);
		progressSilder.setBounds(350, 35, 500, 20);
		this.add(progressSilder);
		//头部时间显示位置及其设置
		progressLabel = new JLabel("00:00");
		progressLabel.setBounds(355, 50, 500, 20);
		progressLabel.setForeground(Color.yellow);
		progressLabel.setOpaque(false);
		progressLabel.setFocusable(false);
		progressLabel.setFont(new Font("楷体", Font.PLAIN, 17));
		this.add(progressLabel);
		//尾部时间显示位置及其设置
		durationLabel = new JLabel("00:00");
		durationLabel.setForeground(Color.yellow);
		durationLabel.setOpaque(false);
		durationLabel.setFocusable(false);
		durationLabel.setFont(new Font("楷体", Font.PLAIN, 17));
		durationLabel.setBounds(810, 50, 500, 20);
		this.add(durationLabel);
		//拖动值添加
		progressSilder.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				int value = progressSilder.getValue();
				progressLabel.setText(Contants.formatTime(value));
			}
		});
		//实现进度条拖拽功能
		class progressSilderMouseListener extends MouseAdapter {
			@Override//释放
			public void mouseReleased(MouseEvent e) {
				// //拖拽结束根据播放时间调用progressSlider数值设置播放进度seekTo方法传时间
				Mp3PlayerService playerService = Mp3PlayerService.getMp3PlayerService();
				playerService.seekTo(progressSilder.getValue() * 1000);
				//拖拽结束
				isSeeking = false;
			}
			@Override//按下
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//开始拖拽
				isSeeking = true;
			}
		}
		progressSilder.addMouseListener(new progressSilderMouseListener());
		// 音乐音量按钮位置和大小设置
		musicsoundButton = new ImageButton(BASE_PATH + "sound\\Sound_hot_03.png", BASE_PATH + "sound\\Sound_hot_03.png",
				BASE_PATH + "sound\\Sound_hot_03.png", 30, 30);
		musicsoundButton.setBounds(200, 20, 30, Contants.PLAYSOUND_BUTTON_HEIGTH);
		this.add(musicsoundButton);
		// 音乐滚动条位置
		musicsound = new BaseSlider();
		musicsound.setBounds(240, 35, 100, 20);
		musicsound.setOpaque(false);
		musicsound.setFocusable(false);
		musicsound.setMaximum(100);
		this.add(musicsound);
		//获取初始音量
		Mp3PlayerService playerService = Mp3PlayerService.getMp3PlayerService();
		musicsound.setValue(playerService.getVolume());
		//musicsound数值变化更新Mp3PlayerService音量
		musicsound.addChangeListener(new ChangeListener() {
			// 音量按钮
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				playerService.setVolume(musicsound.getValue());
				// 音量按钮移除
				remove(musicsoundButton);
				musicsoundButton = musicsoundButton(musicsound.getValue(), 60, 60);
				musicsoundButton.setBounds(200, 20, 30, Contants.PLAYSOUND_BUTTON_HEIGTH);
				add(musicsoundButton);
				repaint();
			}
		});
	}
	// 音量设置
	public JButton musicsoundButton(Integer volume, Integer width, Integer height) {
		String normalIconPat = BASE_PATH + "sound\\";
		String changeIconPat = BASE_PATH + "sound\\";
		String overIconPat = BASE_PATH + "sound\\";
		if (volume == 0) {
			normalIconPat += "Sound_hot_01.png";
			changeIconPat += "Sound_hot_01.png";
			overIconPat += "Sound_hot_01.png";
		} else if (volume <= 25) {
			normalIconPat += "Sound_hot_02.png";
			changeIconPat += "Sound_hot_02.png";
			overIconPat += "Sound_hot_02.png";
		} else if (volume <= 50) {
			normalIconPat += "Sound_hot_03.png";
			changeIconPat += "Sound_hot_03.png";
			overIconPat += "Sound_hot_03.png";

		} else if (volume <= 100) {
			normalIconPat += "Sound_hot_04.png";
			changeIconPat += "Sound_hot_04.png";
			overIconPat += "Sound_hot_04.png";
		}
		return new ImageButton(normalIconPat, changeIconPat, overIconPat, 30, 30);
	}
	// 播放按钮与暂停按钮循环显示及其功能体现
	private void pudateplayButton(boolean isPause) {
		if (playButton != null) {
			this.remove(playButton);
		}
		if (!isPause) {
			playButton = new ImageButton(BASE_PATH + "pause_def.png", BASE_PATH + "pause_pressed.png",
					BASE_PATH + "pause_rollover.png", Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		} else {
			playButton = new ImageButton(BASE_PATH + "play_def.png", BASE_PATH + "play_pressed.png",
					BASE_PATH + "play_rollover.png", Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		}
		playButton.setBounds(10 + Contants.PLAY_BUTTON_HEIGHT, (Contants.PLAY_BUTTON_HEIGHT) - 35,
				Contants.PLAY_BUTTON_HEIGHT, Contants.PLAY_BUTTON_HEIGHT);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//点击playButton时的动作
				Mp3PlayerService mp3PlayerService = Mp3PlayerService.getMp3PlayerService();
				mp3PlayerService.pause();
			}
		});
		this.add(playButton);
	}
	// 进度更新设置
	@Override
	public void update(Observable o, Object data) {
		Message message = (Message) data;
		if (message.getType() == Message.CURRENT_PLAY_UPDATE) {
			SongInfo CurrentSong = message.getSongInfo();
			//设置最大时长
			progressSilder.setMaximum((int) CurrentSong.getDuration());
			progressSilder.setValue(0);
			durationLabel.setText(Contants.formatTime(CurrentSong.getDuration()));
			pudateplayButton(false);
			repaint();//重绘
		} else if (message.getType() == Message.PLAY_PROGRESS_UPDATE && !isSeeking) {
			/**
			 * 拖曳过程中不更新进度
			 */
			progressSilder.setValue((int) message.getProgress());
		} else if (message.getType() == Message.UPDATE_PAUSE_STATE) {
			//发送改变后的状态给pudateplayButton
			pudateplayButton(message.isPause());
			repaint();
		} else if (message.getType() == Message.PLAY_STOP) {
			// 建立一个新线程中播放下一首歌曲
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					SongInfo songInfo = PlayModelService.getPlayModelService().getNextSong();
					PlayListService.getPlayListService().setCurrentPlay(songInfo);
				}
			});
		}
	}
}
