package dkeep.test;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

import dkeep.logic.*;
import dkeep.logic.Drunken.StateDrunken;
import dkeep.logic.Game.Direction;
import dkeep.logic.Guard.GuardType;

public class testDungeonGameLogic {

	char[][] map = {
			{'X','X','X','X','X'},
			{'X','H',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};

	char[][] map2 ={ 
			{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};

	char[][] map3 ={
			{'X','H'},
			{'I','G'},
			{'X','k'},
			{'X',' '}
	};

	char[][] map4 ={ 
			{'X','X','X','X','X'},
			{'X','H',' ','*','X'},
			{'I',' ',' ','O','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};

	char[][] map5 ={ 
			{'X','X','X','X','X'},
			{'X',' ',' ','*','X'},
			{'I',' ',' ','O','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};

	char[][] map6 = {
			{'X','X','X','X','X'},
			{'X','H',' ','g','X'},
			{'I',' ',' ',' ','X'},
			{'I','k',' ',' ','X'},
			{'X','X','X','X','X'}
	};

	@Test
	public void testMoveHeroIntoToFreeCell(){
		Board board = new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(2);
		Level level = new Level(board, pos_rand);
		assertEquals(1,level.getEntities().get(0).getPosx());
		assertEquals(1,level.getEntities().get(0).getPosy());
		Direction direction = Direction.DOWN;
		level.getEntities().get(0).movement(direction,board);
		assertEquals(2,level.getEntities().get(0).getPosx());
		assertEquals(1,level.getEntities().get(0).getPosy());
	}

	@Test
	public void testHeroIsCapturedByGuard(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		assertFalse(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		Direction direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertTrue(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertTrue(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
	}

	@Test
	public void testHeroIsCapturedByClub(){
		Board board= new Board(map4);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		assertFalse(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		Direction direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertTrue(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertTrue(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
	}


	@Test
	public void testAdjacentPositionOgre(){
		Board board= new Board(map2);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		assertFalse(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		Direction direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertFalse(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
		direction = Direction.RIGHT;
		game.getLevel().getEntities().get(0).movement(direction,board);
		assertFalse(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(1)));
	}

	@Test
	public void testExitDoorKeyCell(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		game.entityLever(game.getLevel().getEntities().get(0), game.getLevel());
		assertEquals('H',game.getLevel().getEntities().get(0).getSymbol());
		Direction direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		assertTrue(game.entityLever(game.getLevel().getEntities().get(0), game.getLevel()));
		assertEquals('K',game.getLevel().getEntities().get(0).getSymbol());
	}

	@Test
	public void testFailsToOpenWithoutTheKey(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		Direction direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		assertTrue(game.getLevel().getEntities().get(0).checkIfMovementIsValid(direction, game.getLevel().getBoard()));
		direction = Direction.LEFT;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		assertFalse(game.getLevel().getEntities().get(0).checkIfMovementIsValid(direction, game.getLevel().getBoard()));
	}

	@Test
	public void testOpenDoorWithKey(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		Direction direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		game.entityLever(game.getLevel().getEntities().get(0), game.getLevel());
		assertEquals('K', game.getLevel().getEntities().get(0).getSymbol());
		game.checkLever(game.getLevel().getEntities().get(0), game.getLevel());
		assertEquals('S', game.getLevel().getBoard().getBoard()[3][0]);
	}

	@Test
	public void testPrepareBoard(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		Direction direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		assertTrue(game.getLevel().getBoard().checkLimits(game.getLevel().getEntities().get(0)));
		game.getLevel().getBoard().prepareBoard(game);
	}

	@Test
	public void testWinGame(){
		Board board= new Board(map);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		Direction direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		direction = Direction.DOWN;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		game.entityLever(game.getLevel().getEntities().get(0), game.getLevel());
		game.checkLever(game.getLevel().getEntities().get(0), game.getLevel());
		direction = Direction.LEFT;
		game.getLevel().getEntities().get(0).movement(direction,game.getLevel().getBoard());
		assertTrue(game.changeLevel(level.getEntities().get(0), game.getLevel()));
	}

	@Test
	public void testIfChangeLeverTo$(){
		Board board= new Board(map2);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		level.getEntities().get(1).setPosx(3);
		level.getEntities().get(1).setPosy(1);
		game.entityLever(game.getLevel().getEntities().get(1), game.getLevel());
		assertEquals('$',level.getEntities().get(1).getSymbol());
		level.getEntities().get(1).setPosx(3);
		level.getEntities().get(1).setPosy(2);
		game.entityLever(game.getLevel().getEntities().get(1), game.getLevel());
		assertEquals('O',level.getEntities().get(1).getSymbol());
	}

	@Test(timeout=1000)
	public void testSomeRandomBehavior(){
		Board board= new Board(map2);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Direction direction = Direction.UP;
		int pos_rand2;
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		int x_old = level.getEntities().get(1).getPosx();
		int y_old = level.getEntities().get(1).getPosy();
		boolean outcome1=false, outcome2=false, outcome3 = false, outcome4 = false;

		while(!outcome1 || !outcome2 || !outcome3 || !outcome4){
			Random rand2 = new Random();
			pos_rand2 = rand2.nextInt(4);
			switch(pos_rand2) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.LEFT;
				break;
			case 3:
				direction = Direction.RIGHT;
				break;
			}
			((Ogre)level.getEntities().get(1)).movement(direction, game.getLevel().getBoard(), (Hero)level.getEntities().get(0));
			//cima
			if(level.getEntities().get(1).getPosx() == x_old - 1){
				outcome1=true;
				x_old =level.getEntities().get(1).getPosx();
			}
			//baixo
			else if(level.getEntities().get(1).getPosx() == x_old + 1){
				outcome2=true;
				x_old =level.getEntities().get(1).getPosx();
			}
			//direita
			else if(level.getEntities().get(1).getPosy() == y_old - 1){
				outcome3=true;
				y_old =level.getEntities().get(1).getPosy();
			}
			//esquerda
			else{
				outcome4=true;
				y_old = level.getEntities().get(1).getPosy();
			}
		}

		assertTrue(outcome1);
		assertTrue(outcome2);
		assertTrue(outcome3);
		assertTrue(outcome4);
	}


	@Test(timeout=1000)
	public void testOgreClub(){
		Board board= new Board(map2);
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);
		Direction direction = Direction.UP;
		int pos_rand2;
		boolean outcome1=false, outcome2=false, outcome3 = false, outcome4 = false;

		while(!outcome1 || !outcome2 || !outcome3 || !outcome4){
			Random rand2 = new Random();
			pos_rand2 = rand2.nextInt(4);

			switch(pos_rand2) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.LEFT;
				break;
			case 3:
				direction = Direction.RIGHT;
				break;
			}
			((Ogre)game.getLevel().getEntities().get(1)).movement(direction, game.getLevel().getBoard(), (Hero)game.getLevel().getEntities().get(0));
			game.cleanClub(game.getLevel().getBoard());	
			((Ogre)game.getLevel().getEntities().get(1)).club(game.getLevel().getBoard());

			if(game.getLevel().getBoard().checkLimits(game.getLevel().getEntities().get(1))){
				//cima
				if(game.getLevel().getBoard().getBoard()[game.getLevel().getEntities().get(1).getPosx()-1][game.getLevel().getEntities().get(1).getPosy()] == '*'){
					outcome1=true;
				}
				//baixo
				else if(game.getLevel().getBoard().getBoard()[game.getLevel().getEntities().get(1).getPosx()+1][game.getLevel().getEntities().get(1).getPosy()] == '*'){
					outcome2=true;
				}
				//direita
				else if(game.getLevel().getBoard().getBoard()[game.getLevel().getEntities().get(1).getPosx()][game.getLevel().getEntities().get(1).getPosy()-1] == '*'){
					outcome3=true;
				}
				//esquerda
				else if(game.getLevel().getBoard().getBoard()[game.getLevel().getEntities().get(1).getPosx()][game.getLevel().getEntities().get(1).getPosy()+1] == '*'){
					outcome4=true;
				}
			}

		}

		assertTrue(outcome1);
		assertTrue(outcome2);
		assertTrue(outcome3);
		assertTrue(outcome4);
	}

	@Test
	public void testRaffleGuard(){

		boolean outcome1=false, outcome2=false, outcome3 = false;
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);

		Entity g=  Guard.raffleGuard(pos_rand,5,5);
		while(!outcome1 || !outcome2 || !outcome3){
			rand = new Random();
			pos_rand = rand.nextInt(3);

			g=  Guard.raffleGuard(pos_rand,5,5);

			if(g instanceof Drunken)
				outcome1=true;
			else if(g instanceof Rookie)
				outcome2=true;
			else
				outcome3=true;
		}

		assertTrue(outcome1);
		assertTrue(outcome2);
		assertTrue(outcome3);
	}

	@Test
	public void ogreStun(){
		Board board= new Board(map2);

		int pos_rand2, pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);
		Direction direction = Direction.UP;
		Level level = new Level(board, pos_rand);
		Game game= new Game(level);

		while(game.getLevel().getEntities().get(1).getSymbol() != '8'){
			Random rand2 = new Random();
			pos_rand2 = rand2.nextInt(4);

			switch(pos_rand2) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.LEFT;
				break;
			case 3:
				direction = Direction.RIGHT;
				break;
			}
			game.getLevel().getEntities().get(0).setSymbol('K');
			((Ogre)game.getLevel().getEntities().get(1)).movement(direction, game.getLevel().getBoard(), (Hero)game.getLevel().getEntities().get(0));
		}
		assertEquals('8', game.getLevel().getEntities().get(1).getSymbol());

	}


	@Test
	public void testMovementGuard(){

		boolean outcome1=false, outcome2=false, outcome3 = false;
		int pos_rand, pos_rand2;

		Direction direction= Direction.UP;
		Board board=  new Board(map);

		while(!outcome1 || !outcome2 || !outcome3){

			Random rand = new Random();
			pos_rand = rand.nextInt(3);

			Entity g = Guard.raffleGuard(pos_rand, 5, 5);
			Random rand2 = new Random();
			pos_rand2 = rand2.nextInt(4);

			switch(pos_rand2) {
			case 0:
				direction = Direction.UP;
				break;
			case 1:
				direction = Direction.DOWN;
				break;
			case 2:
				direction = Direction.LEFT;
				break;
			case 3:
				direction = Direction.RIGHT;
				break;

			}
			if(g instanceof Drunken){
				outcome1=true;
				g.movement(direction,board);
				g.movement(direction,board);
				g.movement(direction,board);
			}
			else if(g instanceof Rookie){
				outcome2=true;
				g.movement(direction, board );
				g.movement(direction,board);
				g.movement(direction,board);
			}
			else{
				outcome3=true;
				g.movement(direction, board );
				g.movement(direction,board);
				g.movement(direction,board);
			}
		}

		assertTrue(outcome1);
		assertTrue(outcome2);
		assertTrue(outcome3);
	}


	@Test
	public void testPrintBoardToString(){

		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);

		Board board= new Board(map3);
		Level level= new Level(board, pos_rand);
		Game game = new Game(level);


		Board b= new Board(map3);
		Level lv= new Level(b, pos_rand);
		Game game2 = new Game(lv);

		assertEquals(game.getLevel().getBoard().printBoardToString(game),game2.getLevel().getBoard().printBoardToString(game2));
	}

	@Test
	public void checkLimitsBoard(){

		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);

		Board board= new Board(map3);
		Level level= new Level(board, pos_rand);
		Game game = new Game(level);

		assertFalse(game.getLevel().getBoard().checkLimits(game.getLevel().getEntities().get(0)));
	}

	@Test
	public void testIfAdjacentPosition(){
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);

		Board board= new Board(map);
		Level level= new Level(board, pos_rand);
		Game game = new Game(level);

		assertTrue(game.getLevel().getBoard().conditionAdjacentEntityX(game.getLevel().getEntities().get(0), game.getLevel().getBoard().getBoard().length-1, game.getLevel().getBoard().getBoard()[0].length-1));
		assertTrue(game.getLevel().getBoard().conditionAdjacentEntityY(game.getLevel().getEntities().get(0), game.getLevel().getBoard().getBoard().length-1, game.getLevel().getBoard().getBoard()[0].length-1));
	}

	@Test
	public void testDrunkenBoard(){
		int pos_rand;
		Random rand = new Random();
		pos_rand = rand.nextInt(3);

		Board board= new Board(map6);
		Level level= new Level(board, pos_rand);
		Game game = new Game(level);


		Board b= new Board(map6);
		Level lv= new Level(b, pos_rand);
		Game game2 = new Game(lv);

		assertEquals(game.getLevel().getBoard().printBoardToString(game),game2.getLevel().getBoard().printBoardToString(game2));

	}
	@Test
	public void testGuardTypeAndLevel(){
		Guard g = new Guard(0, 0);

		g.setGuardType(g.guardType.Rookie);
		assertEquals(g.guardType.Rookie, g.getGuardType());

		Level l = new Level(null, null, 0);

		l.setLevel(1);
		assertEquals(1, l.getLevel());
	}

	
}
