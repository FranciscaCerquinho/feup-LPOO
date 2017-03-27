package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;

public class GameGraphics extends JPanel{

	private final int width=360, height=360;

	private BufferedImage hero;
	private BufferedImage ogre;
	private BufferedImage lever;
	private BufferedImage club;
	private BufferedImage floor;
	private BufferedImage guard;
	private BufferedImage wall;
	private BufferedImage guard_asleep;
	private BufferedImage door;
	private BufferedImage open_door;
	private BufferedImage hero_armed;
	private BufferedImage hero_a;
	private BufferedImage ogre_lever;
	private BufferedImage stun;
	private BufferedImage hero_lose;
	private BufferedImage hero_win;
	private BufferedImage init;
	private BufferedImage invalidOgres;
	private BufferedImage defaulti;
	private String boardToString= null;
	private boolean lose =false;
	private boolean win=false;
	private boolean invalid_ogres =false;
	private int nrRows,nrCols;


	public GameGraphics(){
		super();
		try {hero =  ImageIO.read(new File("images/jerryFront.png"));
		floor =  ImageIO.read(new File("images/floor.png"));
		wall =  ImageIO.read(new File("images/wall.png"));
		guard =  ImageIO.read(new File("images/tomFront.png"));
		lever =  ImageIO.read(new File("images/cheese.png"));
		door =  ImageIO.read(new File("images/door.png"));
		guard_asleep =  ImageIO.read(new File("images/tomsleep.png"));
		ogre =  ImageIO.read(new File("images/droopy.png"));
		club =  ImageIO.read(new File("images/bone.png"));
		hero_armed =  ImageIO.read(new File("images/tomK.png"));
		hero_a =  ImageIO.read(new File("images/tomA.png"));
		open_door =  ImageIO.read(new File("images/openDoor.png"));
		hero_lose =  ImageIO.read(new File("images/lose.png"));
		hero_win =  ImageIO.read(new File("images/win.png"));
		ogre_lever =  ImageIO.read(new File("images/cifrao.png"));
		init =  ImageIO.read(new File("images/menu.png"));
		invalidOgres =  ImageIO.read(new File("images/invalidNumberOfOgres.png"));
		stun =  ImageIO.read(new File("images/stunned.png"));} catch (IOException e) { e.printStackTrace();}
	}

	private Image getImage(char c) {
		switch(c){case 'H': return hero;
		case 'X': return wall;
		case 'I': return door;
		case 'S': return open_door;
		case 'O': return ogre;
		case 'A': return hero_a;
		case 'g': return guard_asleep;
		case 'G': return guard;
		case 'k': return lever;
		case 'K': return hero_armed;
		case '*': return club;
		case '8': return stun;
		case ' ': return floor;
		case '$': return ogre_lever;
		default: return defaulti;}
	}

	public void updateGame(String string, int Rows, int Cols){
		boardToString =string;
		nrRows= Rows;
		nrCols= Cols;
		repaint();
	}

	protected void drawBoardToString(Graphics g){
		int x=0;
		int y=0;
		if(boardToString == null){ g.drawImage(init ,0,0, width,height,null);
		return;}
		for(int i =0; i < boardToString.length();i++){
			if(boardToString.charAt(i)=='\n'){
				y++;
				x=0;
				continue;}
			g.drawImage(getImage(boardToString.charAt(i)),x*(width/nrCols),y*(height/nrRows),(width/nrCols),(height/nrRows),null);
			x++;}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x=0;
		int y=0;
		if(lose){g.drawImage(hero_lose ,0,0, width,height,null);
		lose=false;
		return;}
		if(win){g.drawImage(hero_win ,0,0, width,height,null);
		win=false;
		return;}
		if(invalid_ogres){g.drawImage(invalidOgres ,0,0, width,height,null);
		invalid_ogres=false;
		return;}
		drawBoardToString(g);
	}

	public void lose(){
		lose=true;
	}

	public void win(){
		win=true;
	}

	public void invalidNrOgres(){
		invalid_ogres=true;
	}
}
