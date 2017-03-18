import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the JFrame that pulls everything together. It will contain a model of
 * the board that will be used to analyze the game's state and outcome. It will
 * also hold the logic to determine the winner of each game. This is where the
 * main method will reside.
 *
 */
public class Game extends JFrame {

	/**
	 * Auto-generated by Eclipse
	 */
	private static final long serialVersionUID = 1L;

	private int[][] gameBoard;
	private Board board;
	private JPanel scorePanel;
	private JLabel score;
	/**
	 * This JButton restarts the game
	 */
	private JButton restartGame;
	/**
	 * This can have 1 of 3 values:
	 * <ul>
	 * <li>0: This is the PvP mode</li>
	 * <li>1: This is the EasyAI mode</li>
	 * <li>2: This is the HardAI mode</li>
	 * </ul>
	 * 
	 */
	private int gameMode;

	public static void main(String[] args) {

		new Game().setVisible(true);
	}// end main

	public Game() {
		super("TicTacToe");
		setResizable(false);
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//TODO JOptionPane difficulty selector
		String[] diffs = {"PvP", "Easy", "Hard"};
		gameMode = JOptionPane.showOptionDialog(this,
				"Please select a game mode", 
				"Difficulty Selection", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, 
				null, 
				diffs, diffs[0]);
		
		switch (gameMode) {
		case 0:
			board = new PvPBoard(this);
			break;
		case 1:
			board = new EasyBoard(this);
			break;
		case 2:
			board = new HardBoard(this);
			break;
		case -1:
			System.exit(0);
		default:
			board = new PvPBoard(this);
			break;
		}// end switch
		add(board);

		this.gameBoard = new int[3][3];
		this.scorePanel = new JPanel();
		
		this.scorePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		score = new JLabel("Player X's turn...");
		restartGame = new JButton("Start Over");
		restartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				board.startOver();
				gameBoard = new int[3][3];
				score.setText("Player X's turn...");
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		scorePanel.add(score, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		scorePanel.add(restartGame, c);
		add(scorePanel, BorderLayout.SOUTH);
	}// end constructor

	private boolean gameOver(int player)
	{
		board.endGame();
		switch(player){
		case 0:
			System.out.println("It was a tie!");
			this.score.setText("It's a tie!");
			return true;
		case 1:
			System.out.println("Player X wins!");
			this.score.setText("Player X wins!");
			return true;
		case 2:
			System.out.println("Player O wins!");
			this.score.setText("Player O wins!");
			return true;
		default:
			System.out.println("Idk what happened");
			this.score.setText("Error...");
			return true;
		}//end switch
	}
	
	private boolean streak(int one, int two, int three)
	{
		int check = (one*two*three);
		if (1 == check)
			return gameOver(1);
		else if (8 == check)
			return gameOver(2);
		else
			return false;
	}
	
	private void changeScoreText()
	{
		if(this.score.getText().contains("X"))
			this.score.setText("Player O's turn...");
		else if(this.score.getText().contains("O"))
			this.score.setText("Player X's turn...");
	}
	
	private boolean isBoardFilled()
	{
		for(int r = 0; r < 3; r++)
			for(int c = 0; c < 3; c++)
				if(gameBoard[r][c] == 0) return false;
		
		return gameOver(0);
	}//end isBoardFilled

	private boolean isGameOver() {
		// This loops checks each row/col for a streak
		for (int var = 0; var < 3; var++)
			if(streak(gameBoard[var][0], gameBoard[var][1], gameBoard[var][2]) ||
			   streak(gameBoard[0][var], gameBoard[1][var], gameBoard[2][var]) )
				return true;
		
		//diagonal streaks
		if(streak(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]))
			return true;
		else if(streak(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]))
			return true;

		return this.isBoardFilled();
	}// end gameover

	/**
	 * This triggers the actions that take place whenever a player takes their turn
	 * @param player The number of the player who's turn it is (1:X, 2:O)
	 * @param row The row of the button that was pressed
	 * @param col The col of the button that was pressed
	 * @return whether or not the game is over
	 */
	protected boolean turn(int player, int row, int col) {
		
		gameBoard[row][col] = player;
		boolean ret = isGameOver();
		if(!ret)
			changeScoreText();
		return ret;
	}

}