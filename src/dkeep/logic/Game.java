package dkeep.logic;

import dkeep.cli.UserInteraction;

/**
 * 
 * Game.java - Class that includes rules of the game
 *
 */
public class Game {

	public UserInteraction cli;
	public Level level;

	public enum Direction{
		RIGHT,LEFT,UP,DOWN
	}
	
	/**
	 * Constructs game
	 * @param level
	 * 				level of the game
	 */
	public Game(Level level){
		this.level=level;
	}

	/**
	 * Method that changes the doors to stairs when the hero reaches the lever
	 * @param e
	 * 			entity
	 * @param lv
	 * 			level of the game
	 */
	public void checkLever(Entity e, Level lv){
		if(lv.getBoard().getBoard()[e.getPosx()][e.getPosy()] == 'k'){
			e.setSymbol('K');
			int row_aux = lv.getBoard().getBoard().length;
			int col_aux = lv.getBoard().getBoard()[0].length;
			for(int i = 0; i < row_aux; i++){
				for(int j = 0; j < col_aux; j++){
					if(j == lv.getBoard().getBoard().length || j == 0 || i == lv.getBoard().getBoard().length || i == 0)
					if(lv.getBoard().getBoard()[i][j] == 'I')
						lv.getBoard().getBoard()[i][j] = 'S';}} }	
	}
	
	/**
	 * Method that checks if entity's position is in the place of the stairs
	 * @param e
	 * 			entity
	 * @param lv
	 * 			level of the game
	 * @return true if entity's position is in the place of the stairs
	 */
	public boolean changeLevel(Entity e, Level lv){

		if(lv.getBoard().getBoard()[e.getPosx()][e.getPosy()] == 'S')
			return true;
		else
			return false;

	}
	
	/**
	 * Method responsible for changing the entity symbol when moving to the lever
	 * @param e
	 * 			entity
	 * @param lv
	 * 			level of the game
	 */
	public void changeOfSymbol(Entity e, Level lv){
		if(e instanceof Ogre){
			if(!((Ogre) e).getKey()){
				e.setSymbol('$');
				((Ogre) e).setKey(true);}
			else
				e.setSymbol('O');}
		else if(e instanceof Hero)
			e.setSymbol('K');
	}

	/**
	 *  Method that checks if entity's position is in the place of the stairs
	 * @param e
	 * 			entity
	 * @param lv
	 * 			level
	 * @return true if if entity's position is in the place of the stairs
	 */
	public boolean entityLever(Entity e, Level lv){
		if(lv.getBoard().getBoard()[e.getPosx()][e.getPosy()] == 'k'){
			changeOfSymbol(e, lv);
			return true;}
		
		else if(e instanceof Ogre){
			if(((Ogre) e).getKey())
			e.setSymbol('O');}
		
		return false;
	}

	/**
	 * Method that clean the old club's position
	 * @param b
	 * 			board
	 */
	public void cleanClub(Board b){
		
		int row= b.getBoard().length;
		int col= b.getBoard()[0].length;
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(b.getBoard()[i][j] == '*')
					b.getBoard()[i][j]=' ';}
		}
	}

	/**
	 * @return level
	 */
	public Level getLevel() {
		return level;
	}
}
