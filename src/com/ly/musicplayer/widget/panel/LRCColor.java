package com.ly.musicplayer.widget.panel;
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.Font;
	import java.awt.FontMetrics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.RenderingHints;
	import java.awt.image.BufferedImage;
	 
	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	 
	public class LRCColor{
	 
		private BufferedImage buffImg;
		
		/**
		 * @param label 显示字体的JLabel
		 * @param c1 颜色1
		 * @param c2 颜色2
		 * @param ratio 颜色1与颜色2所占部分的比值
		 */
		public LRCColor(JLabel label, Color c1, Color c2, double ratio){
			
			//获取字符串的宽（显示在屏幕上所占的像素px）
	    	        FontMetrics metrics = label.getFontMetrics(label.getFont());
	    	        int width = metrics.stringWidth(label.getText());
	    	        int height = metrics.getHeight();
	    	        height+=label.getFont().getSize();
	    	
	    	        //构造一个具有指定尺寸及类型为预定义图像类型之一的 BufferedImage
			buffImg = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			BufferedImage buffImg1 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			BufferedImage buffImg2 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			
			//通过BufferedImage创建一个 Graphics2D对象
			Graphics2D g1 = buffImg1.createGraphics();
			Graphics2D g2 = buffImg2.createGraphics();
			
			//设置抗锯齿
			g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			//设置字体
			g1.setFont(label.getFont());
			g2.setFont(label.getFont());
			
			//设置颜色
			g1.setColor(c1);
			g2.setColor(c2);
			
			//画字符串
			g1.drawString(label.getText(), 0, height-label.getFont().getSize());
			g2.drawString(label.getText(), 0, height-label.getFont().getSize());
			
			//按照比例清除相关的像素点
			if(ratio<1 && ratio>0){
				
				int rgb = 0x00000000;
				
				//清除buffImg1
				for(int y=0;y<height;y++){
					for(int x=width-1;x>=width*ratio;x--){
						buffImg1.setRGB(x, y, rgb);
					}
				}
				
				//清除buffImg2
				for(int y=0;y<height;y++){
					for(int x=0;x<width*ratio;x++){
						buffImg2.setRGB(x, y, rgb);
					}
				}
				
				//写入buffImg
				for(int y=0;y<height;y++){
					for(int x=0;x<width*ratio;x++){
						buffImg.setRGB(x,y,buffImg1.getRGB(x, y));
					}
				}
				for(int y=0;y<height;y++){
					for(int x=width-1;x>=width*ratio;x--){
						buffImg.setRGB(x,y,buffImg2.getRGB(x, y));
					}
				}
			}
		}
	 
		/**
		 * 获取处理完的ImageIcon
		 * @return
		 */
		public ImageIcon getImageIcon(){
			
			Image img = buffImg;
			ImageIcon imgIcon = new ImageIcon(img);
			
			return imgIcon;
		}
		
		public static void main(String[] args) {
			
			JFrame jf = new JFrame();
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(300, 200);
			jf.setLocationRelativeTo(null);
			jf.setLayout(new BorderLayout());
			
			JLabel jl1 = new JLabel("梦");
			//JLabel jl1 = new JLabel("梦里梦到醒不来的梦");
			jl1.setFont(new Font("幼圆",Font.PLAIN,30));
			LRCColor stc = new LRCColor(jl1, Color.RED, Color.gray, 0.7);
			
			//获取处理完的图片并显示
			JLabel jl2 = new JLabel(stc.getImageIcon());
			jf.add(jl2, BorderLayout.CENTER);
			
			jf.setVisible(true);
		}
		
		
	}
	

