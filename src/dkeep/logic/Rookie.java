package dkeep.logic;

import dkeep.logic.Game.Direction;
import dkeep.logic.Guard;

/**
 * 
 * Rookie.java - A Subclass of Guard
 *
 */
public class Rookie extends Guard{

	private int index = 0;

	/**
	 * Constructs and initializes index of this type of guard
	 * @param posx
	 * 				represents the coordinate x of the position of the Rookie
	 * @param posy
	 * 				represents the coordinate y of the position of the Rookie
	 */
	public Rookie(int posx, int posy) {
		super(posx,posy);
	}
	/**
	 * @return Rookie
	 */
	public Rookie getRookie(){
		return this;
	}

	/**
	 * Methods responsible to define the type of the movement's Rookie
	 * @param direction
	 * 					direction in which Rookie should move
	 * @param b
	 * 			board where the Rookie moves
	 */
	public void movement(Direction direction, Board b){

		direction = directions[index];

		if(index == 23)
			index = 0;
		else
			index++;

		moveDirection(direction);

	}
}