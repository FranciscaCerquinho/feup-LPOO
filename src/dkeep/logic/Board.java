package dkeep.logic;
import java.util.ArrayList;

import dkeep.logic.Guard;
import dkeep.logic.Drunken.StateDrunken;
import dkeep.logic.Drunken;
/**
 * 
 * Board.java - Class that represents the board of the game
 *
 */
public class Board {

	private char map[][];

/**
 * Constructs board
 * @param b
 * 				game board
 */
	public Board(char b[][]){
		map=copy(b);
	}

	/**
	 * @return board
	 */
	public char[][] getBoard(){
		return map;
	}
	
	/**
	 * Method that checks whether an entity is within the limits of lines
	 * @param e
	 * 			entity to check
	 * @param row
	 * 				row of board
	 * @param col
	 * 				column of board
	 * @return true if an entity is within the limits of lines
	 */
	public boolean conditionAdjacentEntityX(Entity e, int row, int col){
		return (e.getPosx()-1 <= row && e.getPosx()-1 >= 0)
				&& (e.getPosx()+1 <= row && e.getPosx()+1 >= 0);
	}
	
	/**
	 * Method that checks whether an entity is within the limits of columns
	 * @param e
	 * 			entity to check
	 * @param row
	 * 				row of board
	 * @param col
	 * 				column of board
	 * @return true if an entity is within the limits of columns
	 */
	public boolean conditionAdjacentEntityY(Entity e, int row, int col){
		return (e.getPosy()+1 <= col && e.getPosy()+1 >= 0)
				&& (e.getPosy()-1 <= col && e.getPosy()-1 >= 0);
	}
	
	/**
	 * Method that checks whether any entity is within the boundaries of the board
	 * @param e
	 * 			entity to check
	 * @param row
	 * 				row of board
	 * @param col
	 * 				column of board
	 * @return true if an entity is within the boundaries of the board
	 */
	public boolean conditionEntity(Entity e, int row, int col){
		return (e.getPosy() == col || e.getPosy() == 0
				|| e.getPosx() == row || e.getPosx() == 0);
	}
	
	/**
	 * Method that checks the limits of the board
	 * @param e
	 * 			entity to check
	 * @return true if all entities respects the limits of the board
	 */
	public boolean checkLimits(Entity e){

		int row= map.length-1;
		int col= map[0].length-1;

		if (conditionEntity(e, row, col))
			return false;
		else if (conditionAdjacentEntityX(e, row,col) && conditionAdjacentEntityY(e, row,col))
			return true;

		else
			return false;
	}
	
	/**
	 * Method that prints on the board the change of the symbol of the entities 
	 * when they are in the same position of the lever
	 * @param game
	 */
	public void prepareBoard(Game game){
		ArrayList<Entity> e = game.getLevel().getEntities();
		if(game.getLevel().getBoard().checkLimits(e.get(0)))
			game.checkLever(e.get(0), game.getLevel());
		for(int i=0; i < e.size(); i++)
			game.entityLever(e.get(i), game.getLevel());
	}
	
	/**
	 * Method that prints on the board all entities
	 * @param game
	 * @param map
	 * 				board where print
	 * @param e
	 * 			array list where are all entities
	 */
	public void printEntities(Game game, char[][] map, ArrayList<Entity> e){
		for(int i=0; i < e.size();i++){
			if(e.get(i) instanceof Guard){
				if(e.get(i) instanceof Drunken){
					if(((Drunken)e.get(i)).getState() == StateDrunken.G)
						map[e.get(i).getPosx()][e.get(i).getPosy()]=e.get(i).getSymbol();
					else{ game.getLevel().getEntities().get(i).setSymbol('g');
						map[e.get(i).getPosx()][e.get(i).getPosy()]=e.get(i).getSymbol();} }
				else
					map[e.get(i).getPosx()][e.get(i).getPosy()]=e.get(i).getSymbol();}
			else
				map[e.get(i).getPosx()][e.get(i).getPosy()]=e.get(i).getSymbol(); }
	}

	/**
	 * Method that convert board to string
	 * @param game
	 * @return string that represents the board
	 */
	public String printBoardToString(Game game){
		String gamestring="";
		char[][] map = new char[10][10];
		int row = game.getLevel().getBoard().getBoard().length;
		int col = game.getLevel().getBoard().getBoard()[0].length;
		ArrayList<Entity> e = game.getLevel().getEntities();
		prepareBoard(game);
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				map[i][j] = game.getLevel().getBoard().getBoard()[i][j];} }	
		printEntities(game, map, e);
		for(int i=0; i< map.length;i++){
			for(int j=0; j < map[0].length;j++){
				gamestring+=map[i][j];}
			gamestring+='\n';}
		gamestring += '\n';	
		return gamestring;
	}
	
	/**
	 * Method that makes a copy of board
	 * @param layout
	 * @return copy of board
	 */
	public char[][] copy(char[][] layout){
		char[][] copy = new char[layout.length][layout[0].length];
		for(int i=0;i<layout.length;i++){
			for(int j=0; j<layout[0].length;j++){
				copy[i][j]=layout[i][j];
			}
		}
		return copy;
	}
}
