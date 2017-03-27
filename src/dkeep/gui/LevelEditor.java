package dkeep.gui;

import javax.swing.JFrame;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.awt.event.MouseMotionAdapter;

public class LevelEditor extends JFrame{

	private JButton btnJerry;
	private JButton btnCheese;
	private JButton btnDog;
	private JButton btnFloor;
	private JButton btnDoor;
	private JButton btnWall;
	private JButton btnExit; 
	private JButton btnStart;
	private JComboBox comboBox_columns, comboBox_rows, comboBox_level;
	private JLabel lblColumns,lblRow, lblLevel;
	private JPanel panel,panel_1;
	private int size_x=1;
	private int size_y=1;
	private final int width=405, height=348;
	private char character_selected;
	private BufferedImage defaulti;
	private boolean cheese_placed=false, jerry_placed=false;
	private char[][] board;
	private JButton btnSaveLevel;
	private BufferedWriter fw;
	private int level;

	public void frameButtonJerry(){
		btnJerry = new JButton("Jerry");
		btnJerry.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				character_selected= 'H';
			}
		});
		btnJerry.setBounds(543, 53, 89, 23);
		getContentPane().add(btnJerry);
	}

	public void frameButtonCheese(){
		btnCheese = new JButton("Cheese");
		btnCheese.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				character_selected= 'k';
			}
		});
		btnCheese.setBounds(543, 87, 89, 23);
		getContentPane().add(btnCheese);
	}

	public void frameButtonDog(){
		btnDog = new JButton("Dog");
		btnDog.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				character_selected= 'O';
			}
		});
		btnDog.setBounds(543, 121, 89, 23);
		getContentPane().add(btnDog);
	}

	public void frameButtonFloor(){
		btnFloor = new JButton("Floor");
		btnFloor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				character_selected= ' ';
			}
		});
		btnFloor.setBounds(543, 155, 89, 23);
		getContentPane().add(btnFloor);
	}

	public void frameButtonDoor(){
		btnDoor = new JButton("Door");
		btnDoor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				character_selected= 'I';
			}
		});
		btnDoor.setBounds(543, 189, 89, 23);
		getContentPane().add(btnDoor);
	}

	public void frameButtonWall(){
		btnWall = new JButton("Wall");
		btnWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				character_selected= 'X';
			}
		});
		btnWall.setBounds(543, 223, 89, 23);
		getContentPane().add(btnWall);
	}

	public void frameButtonSaveLevel(){
		btnSaveLevel = new JButton("Save level");
		btnSaveLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("images/level.txt"), "utf-8"));
					createBoardToFile();
					fw.close();} catch (IOException e1) {
					e1.printStackTrace();}	} });
		btnSaveLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSaveLevel.setBounds(520, 427, 112, 29);
		getContentPane().add(btnSaveLevel);
	}

	public void frameToRows(){
		lblRow = new JLabel("Rows");
		lblRow.setBounds(0, 59, 78, 20);
		panel.add(lblRow);
		lblRow.setFont(new Font("Tahoma", Font.PLAIN, 14));

		comboBox_rows = new JComboBox();
		comboBox_rows.setBounds(88, 61, 128, 20);
		panel.add(comboBox_rows);
		comboBox_rows.setModel(new DefaultComboBoxModel(new Integer[] {5,6,7,8,9,10}));
	}

	public void frameToCols(){
		comboBox_columns = new JComboBox();
		comboBox_columns.setBounds(88, 2, 128, 20);
		panel.add(comboBox_columns);
		comboBox_columns.setModel(new DefaultComboBoxModel(new Integer[] {5,6,7,8,9,10}));

		lblColumns = new JLabel("Columns");
		lblColumns.setBounds(0, 0, 78, 20);
		panel.add(lblColumns);
		lblColumns.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}

	public void frameToLevel(){
		comboBox_level = new JComboBox();
		comboBox_level.setBounds(88, 138, 128, 20);
		panel.add(comboBox_level);
		comboBox_level.setModel(new DefaultComboBoxModel(new Integer[] {1,2}));

		lblLevel = new JLabel("Level");
		lblLevel.setBounds(0, 136, 78, 20);
		panel.add(lblLevel);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	public void frameButtonExit(){
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(76, 425, 63, 32);
		getContentPane().add(btnExit);
	}
	
	public void frameButtonStartEditor(){
		btnStart = new JButton("Start Editor");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {panel_1.setEnabled(true);
				panel.setVisible(false);
				int nRows = Integer.parseInt(comboBox_rows.getSelectedItem().toString());
				int nCols = Integer.parseInt(comboBox_columns.getSelectedItem().toString());
				level = Integer.parseInt(comboBox_level.getSelectedItem().toString());
				size_x = width/nRows;
				size_y = height/nCols;
				board = new char[nRows][nCols];}});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStart.setBounds(183, 425, 112, 32);
		getContentPane().add(btnStart);
	}
	
	public boolean conditionsToDrawOnLimits(int square_x, int square_y){
		return (square_x !=0 && square_x != board.length-1 && square_y !=0 && square_y != board.length-1);
	}
	
	
	public void conditionsEditor(int square_x, int square_y){
		if(checkIfLevelIsValid(square_x,square_y))
			drawCharacterSelected(square_x,square_y);
		else if(conditionsToDrawOnLimits(square_x, square_y))
			drawCharacterSelected(square_x,square_y);
	}

	
	public void frameToEditor(){
		panel_1 = new JPanel();
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent mouse) {
				if(!panel_1.isEnabled())
					return;
				int square_x, square_y;
				square_y = mouse.getX()/size_x;
				square_x = mouse.getY()/size_y;
				conditionsEditor(square_x, square_y); } });
		panel_1.setEnabled(false);
		panel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent mouse) {
				if(!panel_1.isEnabled())
					return;
				int square_x, square_y;
				square_x = mouse.getX()/size_x;
				square_y = mouse.getY()/size_y;
				conditionsEditor(square_x, square_y);} });
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(22, 50, width, height);
		getContentPane().add(panel_1);
	}
	
	public void frameTitle(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Level Editor");
		setBounds(100, 100, 730, 542);
	}

	public LevelEditor() {
		frameTitle();
		frameButtonJerry();
		frameButtonCheese();
		frameButtonDog();	
		frameButtonFloor();
		frameButtonDoor();
		frameButtonWall();

		panel = new JPanel();
		panel.setBounds(46, 74, 265, 213);
		getContentPane().add(panel);
		panel.setLayout(null);

		frameToCols();
		frameToRows();
		frameToLevel();
		frameButtonExit();
		frameButtonStartEditor();
		frameToEditor();
		frameButtonSaveLevel();

	}

	private Image getImage(char c) {
		try{ switch(c){
			case 'H': return ImageIO.read(new File("images/jerryFront.png"));
			case 'X': return ImageIO.read(new File("images/wall.png"));
			case 'I': return ImageIO.read(new File("images/door.png"));
			case 'S': return ImageIO.read(new File("images/openDoor.png"));
			case 'O': return ImageIO.read(new File("images/droopy.png"));
			case 'A': return ImageIO.read(new File("images/tomA.png"));
			case 'g': return ImageIO.read(new File("images/tomsleep.png"));
			case 'G': return ImageIO.read(new File("images/tomFront.png"));
			case 'k': return ImageIO.read(new File("images/cheese.png"));
			case 'K': return ImageIO.read(new File("images/tomK.png"));
			case '*': return ImageIO.read(new File("images/bone.png"));
			case '8': return ImageIO.read(new File("images/stunned.png"));
			case ' ': return ImageIO.read(new File("images/floor.png"));
			case '$': return ImageIO.read(new File("images/cifrao.png"));
			default: return defaulti; } } catch (IOException e) { e.printStackTrace(); }
		return null;
	}

	public void createBoardToFile() throws IOException{

		String nRows = comboBox_rows.getSelectedItem().toString();
		String nCols = comboBox_columns.getSelectedItem().toString();
		String lv = comboBox_level.getSelectedItem().toString();

		fw.write(nRows);
		fw.newLine();
		fw.write(nCols);
		fw.newLine();
		fw.write(lv);
		fw.newLine();

		for(int i=0; i < board.length;i++){
			for(int j=0; j< board[0].length;j++){
				fw.write(board[i][j]+"");}
			fw.newLine();}
	}
	
	public boolean checkCharacterSelected(){
		return (character_selected == 'X' || character_selected== 'I');
	}
	
	public boolean limitsBoard(int x, int y){
		return (x == 0 || x == board.length-1 || y == 0 || y == board.length-1);
	}

	public boolean checkIfLevelIsValid(int x, int y){

		if(limitsBoard(x, y)){
			if(checkCharacterSelected())
				return true;
			else
				return false;}
		else
			return false;

	}

	public void drawCharacterSelected(int square_x, int square_y){
		if(character_selected == 'H'){
			if(!jerry_placed){ panel_1.getGraphics().drawImage(getImage(character_selected), square_x*size_x, square_y*size_y, size_x, size_y, null);
				board[square_x][square_y]=character_selected;
				jerry_placed= true;} }
		else if(character_selected == 'k'){
			if(!cheese_placed){ panel_1.getGraphics().drawImage(getImage(character_selected), square_x*size_x, square_y*size_y, size_x, size_y, null);
				board[square_x][square_y]=character_selected;
				cheese_placed= true;} }
		else if(character_selected == 'O' && level == 2){
			panel_1.getGraphics().drawImage(getImage(character_selected), square_x*size_x, square_y*size_y, size_x, size_y, null);
			board[square_x][square_y]=character_selected;}
		else if(character_selected != 'O'){ panel_1.getGraphics().drawImage(getImage(character_selected), square_x*size_x, square_y*size_y, size_x, size_y, null);
			board[square_x][square_y]=character_selected;}
		revalidate();}
}
