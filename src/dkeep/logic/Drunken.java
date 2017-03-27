package dkeep.logic;

import java.util.Random;
import dkeep.logic.Game.Direction;

/**
 * 
 * Drunken.java - A Subclass of Guard
 *
 */
public class Drunken extends Guard{

	private int index;
	private int move = 1;

	/**
	 * Constructs and initializes index of this type of guard
	 * @param posx
	 * 				Drunken x position
	 * @param posy
	 * 				Drunken y position
	 */
	public Drunken(int posx,int posy) {
		super(posx,posy);
		index = 0;
	}

	public enum StateDrunken{
		G,g;
	}

	private StateDrunken status[] ={StateDrunken.G,StateDrunken.g};

	public StateDrunken state = StateDrunken.G;

	/**
	 * @return state of this guard
	 */
	public StateDrunken getState() {
		return state;
	}

	/**
	 * Methods that sets the state of the Drunken
	 * @param st
	 * 			state of the Drunken
	 */
	public void setStateDrunken(StateDrunken st) {
		this.state = st;
	}

	/**
	 * Methods that reverses Drunken's direction
	 * @return Direction of the Drunken
	 */
	public Direction reverseDrunkenDirection(){
		index--;
		if(index < 0)
			index += directions.length;

		Direction direction = directions[index];

		switch(direction){ case UP: direction= direction.DOWN;
		break;
		case DOWN: direction= direction.UP;
		break;
		case LEFT: direction= direction.RIGHT;
		break;
		case RIGHT: direction= direction.LEFT;
		break;}
		return direction;
	}

	/**
	 * Methods responsible to define the type of the movement's drunken
	 * @param direction
	 * 					direction in which guard should move
	 * @param b
	 * 			board where the Drunken moves
	 */
	public void movement(Direction direction, Board b){
		int status_rand, pos_rand;
		Random rand2 = new Random();
		status_rand = rand2.nextInt(2);
		StateDrunken st = status[status_rand];
		setStateDrunken(st);

		Random rand = new Random();
		pos_rand = rand.nextInt(10);
		direction = directions[index];		
		if(pos_rand == 1) move *= -1;

		if(st == StateDrunken.G){
			setSymbol('G');
			if(move == 1) direction = reverseDrunkenDirection();
			else index++;

			if(index >= directions.length) index -= directions.length;

			moveDirection(direction);}}
}