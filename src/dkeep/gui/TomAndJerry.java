package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dkeep.logic.Board;
import dkeep.logic.Entity;
import dkeep.logic.Game;
import dkeep.logic.Game.Direction;
import dkeep.logic.Hero;
import dkeep.logic.Level;
import dkeep.logic.Ogre;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TomAndJerry {

	static char[][] level1 = {{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'X',' ','I',' ','I',' ','X',' ',' ','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'},
			{'X',' ','I',' ','I',' ','X','k',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};

	static char[][] level2 = {{'X','X','X','X','X','X','X','X','X','X'},
			{'I','H',' ',' ',' ',' ','O',' ','k','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};

	private JFrame frmDungeonKeep;
	private JTextField numberOfOgres;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnLeft;
	private JButton btnDown;
	private JComboBox comboBox;
	private JLabel lblNumberOfOgres;
	private JButton btnNewButton;
	private Board board;
	private Level level;
	private Game game;
	private GameGraphics game_graphics;
	private int guardType;
	private int nrOfOgres;
	private JLabel commentsLabel;
	JButton btnLoadLevel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TomAndJerry window = new TomAndJerry();
					window.frmDungeonKeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TomAndJerry() {
		initialize();
	}

	private void frameDungeon(){
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setTitle("TOM AND JERRY");
		frmDungeonKeep.setBounds(100, 100, 730, 542);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		frmDungeonKeep.setFocusable(true);		
	}

	private void frameAddKeyListener(){
		frmDungeonKeep.addKeyListener(new KeyAdapter() { public void keyPressed(KeyEvent e) { int keyCode = e.getKeyCode();
		if(!checkKeyboardButtons()){ switch( keyCode ) {  case KeyEvent.VK_UP:{ 		
			{ commentsLabel.setText("Jerry moved up" );
			move(Direction.UP);} break;}
		case KeyEvent.VK_DOWN: {
			{ commentsLabel.setText("Jerry moved down" );
			move(Direction.DOWN); } break;}
		case KeyEvent.VK_RIGHT:{
			{ commentsLabel.setText("Jerry moved right" );
			move(Direction.RIGHT); } break;}
		case KeyEvent.VK_LEFT: {
			{ commentsLabel.setText("Jerry moved left" );
			move(Direction.LEFT);} break;}}} }});
	}

	public void conditionsButtonNumberOfOgres(){
		if(Integer.parseInt(numberOfOgres.getText()) > 5){
			numberOfOgres.setText("5");
			nrOfOgres = 5;}
		if(Integer.parseInt(numberOfOgres.getText()) < 1){
			numberOfOgres.setText("1");
			nrOfOgres = 1;}
	}

	public void frameNumberOfOgres(){
		numberOfOgres = new JTextField();
		numberOfOgres.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				try{Integer.parseInt(numberOfOgres.getText());}
				catch(NumberFormatException e){ e.printStackTrace();
				game_graphics.invalidNrOgres();
				numberOfOgres.setText("1");
				nrOfOgres = 1;
				return;}
				conditionsButtonNumberOfOgres();
				nrOfOgres = Integer.parseInt(numberOfOgres.getText());} });

		numberOfOgres.setBounds(139, 17, 91, 20);
		frmDungeonKeep.getContentPane().add(numberOfOgres);
		numberOfOgres.setColumns(10);
	}

	public void frameGuardPersonality(){
		JLabel lblNewLabel = new JLabel("Tom personality");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(20, 55, 111, 20);
		frmDungeonKeep.getContentPane().add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardType = comboBox.getSelectedIndex();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Suspicious", "Drunken", "Rookie"}));
		comboBox.setBounds(139, 55, 127, 20);
		frmDungeonKeep.getContentPane().add(comboBox);
	}

	public void frameButtonLoadLevel(){
		btnLoadLevel = new JButton("Load level");
		btnLoadLevel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnLoadLevel.setForeground(SystemColor.activeCaption);}
			public void mouseExited(MouseEvent e) {
				btnLoadLevel.setForeground(Color.BLACK);}});
		btnLoadLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner input;
				try {String line;
				input = new Scanner(new File("images/level.txt"));
				int rows= Integer.parseInt(input.nextLine());
				int cols= Integer.parseInt(input.nextLine());
				int lvl= Integer.parseInt(input.nextLine());
				char [][] b = new char[rows][cols];
				for(int i =0; i < rows; i++){
					line =input.nextLine();
					char[] line1 = line.toCharArray();
					for(int j=0; j < cols; j++){
						b[i][j]= line1[j];}}				
				frmDungeonKeep.requestFocusInWindow();
				board = new Board(b);
				level = new Level(board,lvl);
				game = new Game(level);
				checkButtons();
				commentsLabel.setText("New Game!");
				game_graphics.updateGame(game.getLevel().getBoard().printBoardToString(game),  game.getLevel().getBoard().getBoard().length, game.getLevel().getBoard().getBoard()[0].length );
				} catch (FileNotFoundException e1) {e1.printStackTrace();}}});
		btnLoadLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadLevel.setBounds(535, 75, 111, 23);
		frmDungeonKeep.getContentPane().add(btnLoadLevel);
	}

	public void frameButtonLevelEditor(){
		btnNewButton = new JButton("Level editor");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(SystemColor.activeCaption);}
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);} });
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LevelEditor levelEditor = new LevelEditor();
				levelEditor.setVisible(true); } });
		btnNewButton.setBounds(535, 53, 111, 23);
		frmDungeonKeep.getContentPane().add(btnNewButton);
	}

	public void initializeNewGame(){
		board = new Board(level1);
		level= new Level(board, guardType);
		game = new Game(level);
		checkButtons();
	}

	public void frameButtonNewGame(){
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addMouseListener(new MouseAdapter() { 
			public void mouseEntered(MouseEvent mouse) { btnNewGame.setForeground(SystemColor.activeCaption);}
			public void mouseExited(MouseEvent mouse) { btnNewGame.setForeground(Color.BLACK);} });

		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				frmDungeonKeep.requestFocusInWindow ();
				initializeNewGame();
				commentsLabel.setText("New Game!");
				game_graphics.updateGame(game.getLevel().getBoard().printBoardToString(game), game.getLevel().getBoard().getBoard().length, game.getLevel().getBoard().getBoard()[0].length);} });
		btnNewGame.setBounds(310, 54, 102, 23);
		frmDungeonKeep.getContentPane().add(btnNewGame);
	}

	public void frameButtonExit(){
		JButton blbExit = new JButton("Exit");
		blbExit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				blbExit.setForeground(SystemColor.activeCaption);}
			public void mouseExited(MouseEvent e) {
				blbExit.setForeground(Color.BLACK);} });
		blbExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);} });
		blbExit.setBounds(526, 425, 89, 23);
		frmDungeonKeep.getContentPane().add(blbExit);
	}

	public void frameButtonRight(){
		btnRight = new JButton("Right");
		btnRight.setEnabled(false);
		btnRight.setBounds(578, 245, 90, 28);
		frmDungeonKeep.getContentPane().add(btnRight);

		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				commentsLabel.setText("Jerry moved right" );
				move(Direction.RIGHT);
			}
		});

	}

	public void frameButtonLeft(){
		btnLeft = new JButton("Left");
		btnLeft.setEnabled(false);
		btnLeft.setBounds(465, 245, 90, 28);
		frmDungeonKeep.getContentPane().add(btnLeft);

		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentsLabel.setText("Jerry moved left" );
				move(Direction.LEFT);
			}
		});
	}

	public void frameButtonUp(){
		btnUp = new JButton("Up");
		btnUp.setEnabled(false);
		btnUp.setBounds(526, 205, 90, 28);
		frmDungeonKeep.getContentPane().add(btnUp);

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentsLabel.setText("Jerry moved up" );
				move(Direction.UP);
			}
		});
	}

	public void frameButtonDown(){
		btnDown = new JButton("Down");
		btnDown.setEnabled(false);
		btnDown.setBounds(526, 284, 90, 28);
		frmDungeonKeep.getContentPane().add(btnDown);

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentsLabel.setText("Jerry moved down" );
				move(Direction.DOWN);
			}
		});


	}

	public void frameCommentsLabel(){
		commentsLabel = new JLabel("You can start a new game");
		commentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		commentsLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		commentsLabel.setBounds(20, 459, 360, 33);
		frmDungeonKeep.getContentPane().add(commentsLabel);

	}

	public void frameLabelNumberOfOgres(){
		lblNumberOfOgres = new JLabel("Number of Dogs");
		lblNumberOfOgres.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumberOfOgres.setBounds(20, 11, 121, 33);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
	}

	public void addGameGraphics(){
		frameDungeon();
		frameAddKeyListener();
		game_graphics = new GameGraphics();
		game_graphics.setBounds(20,99,360,360);
		frmDungeonKeep.getContentPane().add(game_graphics);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addGameGraphics();
		frameLabelNumberOfOgres();
		frameNumberOfOgres();	
		frameGuardPersonality();
		frameButtonExit();
		frameButtonDown();
		frameButtonUp();
		frameButtonRight();
		frameButtonLeft();		
		frameCommentsLabel();	
		frameButtonNewGame();
		frameButtonLevelEditor();
		frameButtonLoadLevel();
	}

	public void move(Direction direction){

		for(int i=0; i < level.getEntities().size();i++){
			if(level.getEntities().get(i) instanceof Ogre){
				((Ogre)level.getEntities().get(i)).movement(direction, level.getBoard(),(Hero) level.getEntities().get(0));
				((Ogre)level.getEntities().get(i)).club(game.getLevel().getBoard());
				continue;
			}
			level.getEntities().get(i).movement(direction, level.getBoard());

		}

		gameLogic();
	}

	public void checkEnableButtons(){
		Entity hero = game.getLevel().getEntities().get(0);
		Hero h = (Hero)hero;

		if(!h.checkIfMovementIsValid(Direction.UP, game.getLevel().getBoard()))
			btnUp.setEnabled(false);
		else btnUp.setEnabled(true);

		if(!h.checkIfMovementIsValid(Direction.DOWN, game.getLevel().getBoard()))
			btnDown.setEnabled(false);
		else btnDown.setEnabled(true);

		if(!h.checkIfMovementIsValid(Direction.LEFT, game.getLevel().getBoard()))
			btnLeft.setEnabled(false);
		else btnLeft.setEnabled(true);

		if(!h.checkIfMovementIsValid(Direction.RIGHT, game.getLevel().getBoard()))
			btnRight.setEnabled(false);
		else btnRight.setEnabled(true);
	}
	public void checkButtons(){
		Entity hero = game.getLevel().getEntities().get(0);
		Hero h = (Hero)hero;

		if(game.getLevel().getBoard().checkLimits(h)){
			checkEnableButtons();}
	}

	public void buttonsWhenChangeLevel(){
		if(game.changeLevel(game.getLevel().getEntities().get(0), game.getLevel())){
			int ogres=1;
			if(game.getLevel().getLevel() == 1){
				board = new Board(level2);
				level = new Level(board, guardType);
				game = new Game(level);
				game.getLevel().getEntities().get(0).setSymbol('A');
				while(ogres != nrOfOgres) {
					Ogre o = new Ogre(7,4);
					game.getLevel().getEntities().add(o);
					ogres++;} }
			else{btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnRight.setEnabled(false);
			btnLeft.setEnabled(false);
			commentsLabel.setText("Jerry won!" );
			game_graphics.win();
			return;}}
		else
			checkButtons();
	}

	public void gameLogic(){
		game_graphics.updateGame(game.getLevel().getBoard().printBoardToString(game), game.getLevel().getBoard().getBoard().length, game.getLevel().getBoard().getBoard()[0].length);
		for(int i=1; i < game.getLevel().getEntities().size();i++){
			if(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(i))){
				btnUp.setEnabled(false);
				btnDown.setEnabled(false);
				btnRight.setEnabled(false);
				btnLeft.setEnabled(false);
				commentsLabel.setText("Jerry lost!" );
				game_graphics.lose();
				return;}}
		game.cleanClub(game.getLevel().getBoard());	
		buttonsWhenChangeLevel();
	}

	public boolean checkKeyboardButtons(){
		for(int i=1; i < game.getLevel().getEntities().size();i++){
			if(game.getLevel().checkIfEnds(game.getLevel().getEntities().get(0),game.getLevel().getEntities().get(i)))
				return true;

			return false;
		}

		return false;
	}
}