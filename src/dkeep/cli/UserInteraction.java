package dkeep.cli;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import dkeep.logic.Board;
import dkeep.logic.Hero;
import dkeep.logic.Level;
import dkeep.logic.Guard;
import dkeep.logic.Ogre;
import dkeep.logic.Entity;
import dkeep.logic.Game;
import dkeep.logic.Game.Direction;


public class UserInteraction {

	static char[][] level1 = {{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};

	static char[][] level2 = {{'X','X','X','X','X','X','X','X','X','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};


	public static Game game;
	public static int numberOfOgres;
	public boolean endGame;

	public UserInteraction(){		

	}
	
	public static void main(String[] args) {

		initGame();
		UserInteraction cli = new UserInteraction();
		
		// create level 1
		Board board1 = new Board(level1);
		Entity hero1 = new Hero(1, 1);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Entity guard = Guard.raffleGuard(pos_rand, 1, 8);
		ArrayList<Entity> characters1= new ArrayList<Entity>();
		characters1.add(hero1);
		characters1.add(guard);
		Level lv = new Level(board1, characters1, 1);
		game = new Game(lv);
		//Play lv1_play=new Play(game);
		while(!cli.play());
		if(cli.end()){
			return;//perde no nivel 1
		};
	
		// create level 2
		Board board2 = new Board(level2);
		Entity hero2 = new Hero(7, 1);
		Entity ogre = new Ogre(1, 4);
		Entity ogre2= new Ogre(3, 6);
		ArrayList<Entity> characters2= new ArrayList<Entity>();
		characters2.add(hero2);
		characters2.add(ogre);
		if(numberOfOgres==2)
			characters2.add(ogre2);
		lv.setBoard(board2);
		lv.setLevel(2);		
		lv.setEntities(characters2);
		game = new Game(lv);
	//	Play lv2_play=new Play(game);
		while(!cli.play());
		if(cli.end()){
			return;//perde no nivel 1
		};
	}

	public Direction readDirection(){
		char c;
		System.out.println();
		System.out.println("Please insert a character:");
		System.out.println("To move down, insert 'd'");
		System.out.println("To move up, insert 'u'");
		System.out.println("To move left, insert 'l'");
		System.out.println("To move right, insert 'r'");
		Scanner reader = new Scanner(System.in);
		c = reader.next().charAt(0);
		c = Character.toLowerCase(c);
		while (c != 'r' && c != 'l' && c != 'd' && c != 'u') {
			System.out.println("Invalid character. Try again.");
			c = reader.next().charAt(0);
			c = Character.toLowerCase(c);}
		Direction direction = Direction.UP;
		switch (c) {
		case 'u': direction = Direction.UP;
			break;
		case 'd': direction = Direction.DOWN;
			break;
		case 'l': direction = Direction.LEFT;
			break;
		case 'r': direction = Direction.RIGHT;
			break;
		default:System.out.println("Invalid character");
			break;}
		return direction;
	}
	
	public static void initGame(){
		System.out.println("Welcome to DungeonKeep game!");
		int c;
		
		System.out.println("Please insert a number of Ogres: ");

		Scanner reader = new Scanner(System.in);
		c = reader.nextInt();
		while (c != 1 && c != 2) {
			System.out.println("Invalid character. Try again.");
			c = reader.nextInt();
		}
		
		numberOfOgres=c;
	}
	
	public void moveEntities(Direction direction){
		for(int i=1; i < game.getLevel().getEntities().size();i++){
			if(game.getLevel().getEntities().get(i) instanceof Ogre){
				((Ogre)game.getLevel().getEntities().get(i)).movement(direction, game.getLevel().getBoard(),(Hero)game.getLevel().getEntities().get(0));
				((Ogre)game.getLevel().getEntities().get(i)).club(game.getLevel().getBoard());}
			else
				game.getLevel().getEntities().get(i).movement(direction, game.getLevel().getBoard()); }
	}
	
	public boolean changeLevelOrWin(Game game){
		if(game.changeLevel(game.getLevel().getEntities().get(0), game.getLevel())){
			if(game.getLevel().getEntities().get(1) instanceof Ogre){
				System.out.println("You won! Congratulations!");}
			return true;}
		else return false;
	}
		
	public boolean play() {
		boolean alreadychange=false;
		while (!game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1))) {
			if(changeLevelOrWin(game))
				return true;
			if(!alreadychange){
				if (game.getLevel().getEntities().get(1) instanceof Ogre) {
					game.getLevel().getEntities().get(0).setSymbol('A');
					alreadychange=true;} }
			System.out.print(game.getLevel().getBoard().printBoardToString(game));
			UserInteraction user = new UserInteraction();
			Direction direction = user.readDirection();
			int x = game.getLevel().getEntities().get(0).getPosx();
			int y = game.getLevel().getEntities().get(0).getPosy();
			game.getLevel().getEntities().get(0).movement(direction, game.getLevel().getBoard());
			game.cleanClub(game.getLevel().getBoard());	
			moveEntities(direction); }
		System.out.print(game.getLevel().getBoard().printBoardToString(game));
		System.out.print("You got caught! Game Over!");
		endGame=true;
		return true;
	}

	public boolean end() {
		return endGame;
	}

}