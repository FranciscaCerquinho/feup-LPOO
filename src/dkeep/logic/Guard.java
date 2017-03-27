package dkeep.logic;
import dkeep.logic.Game.Direction;
import dkeep.logic.Drunken;

/**
 * 
 * Guard.java - A subclass of Entity
 *
 */
public class Guard extends Entity{

	private int index = 0;
	private int move = 1;
	
	protected Direction directions[] = {Direction.LEFT, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, 
			Direction.LEFT,Direction.LEFT, Direction.LEFT,Direction.LEFT, Direction.LEFT, Direction.LEFT, Direction.DOWN,
			Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT,Direction.RIGHT, 
			Direction.RIGHT, Direction.UP,  Direction.UP, Direction.UP, Direction.UP, Direction.UP};
	
	private char symbol;
	
	/**
	 * Constructs and initializes the symbol that represents the Guard
	 * @param posx
	 * 				represents the coordinate x of the position of the Guard
	 * @param posy
	 * 				represents the coordinate y of the position of the Guard
	 *
	 */
	public Guard(int posx, int posy) {
		super(posx, posy);
		symbol='G';
	}
	
	public enum GuardType{
		Rookie,Suspicious,Drunken
	}
		
	public GuardType guardType = GuardType.Rookie;
	
	/**
	 * Method that returns guard's type: Drunken, Suspicious or Drunken
	 * @return guardType
	 */
	public GuardType getGuardType(){
		return guardType;
	}
	
	/**
	 * Method that sets guard's type
	 * @param g
	 * 			guard's type
	 */
	public void setGuardType(GuardType g) {
		this.guardType = g;
	}
	
	/**
	 * @return symbol of the guard
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * Method that sets guard's symbol
	 * @param symbol
	 * 				symbol of the guard
	 */
	public void setSymbol(char symbol){
		this.symbol=symbol;
	}
	
	/**
	 * Method responsible to move guard
	 * @param direction
	 * 					direction in which guard should move
	 * @param b
	 * 			board where the Guard moves
	 */
	public void movement(Direction direction, Board b){
		
	}
	
	/**
	 * Method that chooses type of guard
	 * @param button
	 * @param posx
	 * 				guard x position
	 * @param posy
	 * 				guard y position
	 * @return guard's type
	 */
	public static Guard raffleGuard(int button, int posx, int posy){
		Guard g;
		switch(button) {
		case 0: g = new Suspicious(posx,posy);
			break;
		case 1: g = new Drunken(posx,posy);
			break;
		case 2: g = new Rookie(posx,posy);
			break;
		default: g = new Rookie(posx,posy);
		break;
		}	
		return g;
	}
	
	/**
	 * Method responsible to move guard in different directions
	 * @param direction
	 */
	public void moveDirection(Direction direction){
		switch(direction) {
		case UP:
			posx--;
			break;
		case DOWN:
			posx++;
			break;
		case RIGHT:
			posy++;
			break;
		case LEFT:
			posy--;
		}
	}
	
	
}