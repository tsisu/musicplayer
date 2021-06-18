package com.ly.musicplayer.util;

import java.util.Observable;

//消息中心
//1.注册观察者
//2.发送消息给观察者
public class MessageCenter extends Observable {
     //一个系统只能有一个消息中心。消息中心做成单例的
	 //
	 //
	private static MessageCenter messageCenter = null;
      public static MessageCenter getMessageCenter() {
    	  if (messageCenter == null) {
			messageCenter = new MessageCenter();
		}
    	  return messageCenter;
      }
	
	
	public void sendMessage(Message msg) {
	  this.setChanged();//告诉消息中心有状态要更新
	  this.notifyObservers(msg);
		
	
	}
}
