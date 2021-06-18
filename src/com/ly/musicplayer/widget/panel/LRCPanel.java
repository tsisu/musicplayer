package com.ly.musicplayer.widget.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.JPanel;

public class LRCPanel extends JPanel {
     private static final String CURRENT_LINE_COLOR = null;

	public LRCPanel() {
    	 super();
      	 this.setBackground(null);
    	 this.setOpaque(false);
     }
    

     //the lines other than the current line to be displayedprotected final int UP DOWN LINES . 8;

     //the list of lyric statements to be displayed

     protected List<LRCPanel> statements;

     //the index of next statement to be dispalyed in the statementsprotected int index;

     protected Image backgroundImage = null;

     private String backGroundImagePath;

     protected Image bufferImage = null;

     //the size when the bufferImage 1s drawn

     private Dimension bufferedsize;

	private int index;

	private Object drawL;

     public String getBackGroundImagePath() {
    	 return backGroundImagePath;

     }

     public void setBackGroundImagePath(String backGroundImagePath) { 
    	 this .backGroundImagePath =backGroundImagePath;
     }
     public void prepareDisplay(List<LRCPanel> statements) {

    		 this.statements = statements;

    		 this.index = -1;

    		 this.setFont(new Font("微软雅黑",Font.PLAIN, 20));
    }

    		 

			public void displayLyric(int index) {

    		 this.index =index;

    		 this .drawBufferImage();
    		 }

			private void drawBufferImage() {
				// TODO Auto-generated method stub
				
			}
			protected void drawlineInMiddle(int height, Object object, Graphics2D g2d ,String currentLineColor) {

			int width = this.getWidth();

			FontMetrics fm =g2d.getFontMetrics();

		

			int x = (this.getWidth()-fm.stringWidth((String) object)) /2;

			g2d.drawString((String) object, x, height);
}

			protected void drawBufferImage1() {

				Image tempBufferedImage = this . createImage(this . getWidth(),this. getHeight());

				this . bufferedsize = this. getSize();

				if (this . backgroundImage == null) {

				//get background image

				java.net.URL url = getClass(). getResource( this. backGroundImagePath);

				try {
					
					backgroundImage = ImageIO. read(url);

					backgroundImage = backgroundImage . getScaledInstance (this. getWidth()

					,this . getHeight(), 20);

				} catch (IOException ex) {

					ex. printStackTrace();

				}

					Graphics2D g2d = (Graphics2D) tempBufferedImage . getGraphics();

					g2d. setFont(new Font("4", Font . PLAIN, 25));

					g2d . drawImage (this . backgroundImage, 0, 0, this . getWidth(), this . getHeight(), null);

					g2d. setRenderingHint( RenderingHints.KEY_ANTIALIASING,

					RenderingHints.VALUE_ANTIALIAS_ON);

					if (this. statements != null && this . statements.size() != 0) {

					//draw current line

					g2d. setFont(new Font("#", Font.PLAIN, 35));

					this. drawlineInMiddle( this . getHeight() / 2,

					this.statements. get(index) . getLyric(), g2d, this . CURRENT_LINE_COLOR);

					int perHeight = g2d. getFontMetrics().getHeight() + 5;

					g2d. setFont(new Font("楷体", Font . PLAIN, 25));
				}
}
}
			private Object getLyric() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override

			public void paint (Graphics g) {if (this. isVisible( )

			==false) {

			return ;
			}

			
			super.paint(g);

			if (this. bufferImage == null || this. getWidth() !=  this.bufferedsize .getWidth()

			|| this. getHeight() != this. bufferedsize . getHeight()) {

			this . drawBufferImage();

			}

			g.drawImage(bufferImage, 0, 0, null);
}
}