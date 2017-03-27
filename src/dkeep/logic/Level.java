package dkeep.logic;

import java.util.ArrayList;

import dkeep.logic.Drunken.StateDrunken;

/**
 * 
 * Level.java - Class that represents level of the game
 *
 */
public class Level {

	private Board board;
	private int level;
	private ArrayList<Entity> entities;

	/**
	 * Constructs
	 * @param b
	 * 			board of the level
	 * @param entities
	 * 					array list that includes all entities
	 * @param l
	 * 			level
	 */
	public Level(Board b, ArrayList<Entity> entities, int l){
		this.board=b;
		this.level=l;
		this.entities=entities;

	}

	/**
	 * Constructs
	 * @param b
	 * 			board
	 * @param guard
	 */
	public Level(Board b, int guard){
		entities = new ArrayList<Entity>();
		board=b;
		int row= b.getBoard().length;
		int col= b.getBoard()[0].length;
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				if(b.getBoard()[i][j] == 'H'){
					Entity entity_hero= new Hero(i,j);
					entities.add(entity_hero);
					b.getBoard()[i][j]= ' ';}
				else if(b.getBoard()[i][j] == 'G'){
					level=1;
					Entity entity_guard=  Guard.raffleGuard(guard,i,j);
					entities.add(entity_guard);
					b.getBoard()[i][j]= ' ';}
				else if(b.getBoard()[i][j] == 'O'){
					level=2;
					Entity entity_ogre= new Ogre(i,j);
					entities.add(entity_ogre);
					b.getBoard()[i][j]= ' ';} } }
	}

	/**
	 * @return board of the level
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * @return level
	 */
	public int getLevel(){
		return level;
	}

	/**
	 * Method that sets the level
	 * @param lv
	 * 			level
	 */
	public void setLevel(int lv){
		this.level=lv;
	}

	/**
	 * Method that sets the board
	 * @param board
	 * 				board of the level
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return entities Array that includes all game's entities
	 */
	public ArrayList<Entity> getEntities(){
		return entities;
	}

	/**
	 * Method that checks if the hero is next to club
	 * @param hero
	 * @param capture
	 * @return true if the hero has an adjacent position to club
	 */
	public boolean checkIfIsNextClub(Entity hero, Entity capture){
		return ((board.getBoard()[hero.getPosx()-1][hero.getPosy()] == '*') || (board.getBoard()[hero.getPosx()+1][hero.getPosy()] == '*') || (board.getBoard()[hero.getPosx()][hero.getPosy()-1] == '*') || (board.getBoard()[hero.getPosx()][hero.getPosy()+1] == '*'));
	}

	/**
	 * Method responsible for checking that the hero is in an adjacent position on the guard or club's x
	 * @param hero
	 * @param capture
	 * @return true if hero is in an adjacent position on the guard or club's x
	 */
	public boolean checkCaptureX(Entity hero, Entity capture){
		return (hero.getPosx()-1 == capture.getPosx() && hero.getPosy()== capture.getPosy()) 
				|| (hero.getPosx()+1 == capture.getPosx() && hero.getPosy()== capture.getPosy());
	}

	/**
	 * Method responsible for checking that the hero is in an adjacent position on the guard or club's y
	 * @param hero
	 * @param capture
	 * @return true if hero is in an adjacent position on the guard or club's y
	 */
	public boolean checkCaptureY(Entity hero, Entity capture){
		return (hero.getPosx() == capture.getPosx() && hero.getPosy()-1 == capture.getPosy())
				|| (hero.getPosx() == capture.getPosx() && hero.getPosy()+1 == capture.getPosy());
	}
	
	/**
	 * Method responsible for checking that the hero is in a same position as the guard or club
	 * @param hero
	 * @param capture
	 * @return true if hero is in a same position as the guard or club
	 */
	public boolean checkCaptureSamePosition(Entity hero, Entity capture){
		return (hero.getPosx() == capture.getPosx() && hero.getPosy() == capture.getPosy())
				|| (hero.getPosx() == capture.getPosx() && hero.getPosy() == capture.getPosy());
	}
	
	/**
	 * Method that checks if Drunken is asleep
	 * @param capture
	 * 					Drunken
	 * @return true if  Drunken is asleep
	 */
	public boolean checkIsAsleep(Entity capture){
		if(capture instanceof Drunken)
			return (((Drunken)capture).getState() == StateDrunken.g);
		return false;
	}
	
	/**
	 * Method that checks if the hero has been captured
	 * @param hero
	 * @param capture
	 * 				guard or club
	 * @return
	 */
	public boolean checkCapture(Entity hero, Entity capture){
		return (checkCaptureX(hero,capture) || checkCaptureY(hero, capture) || checkCaptureSamePosition(hero, capture));
	}

	/**
	 * Method that checks if the game is over
	 * @param hero
	 * @param capture
	 * 				guard, club or ogre
	 * @return true if the game is over
	 */
	public boolean checkIfEnds(Entity hero, Entity capture){
		if(board.checkLimits(hero)){
			if(checkIsAsleep(capture))
				return false;
			else if(capture instanceof Ogre){
				if(checkIfIsNextClub(hero, capture))
					return true;
				else if(checkCapture(hero, capture))
					return false;

				return false;}
			if(checkCapture(hero, capture))
				return true;}
		return false;
	}

	/**
	 * Method that sets entities
	 * @param entities
	 * 					array list that includes all entities
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities=entities;
	}


}
