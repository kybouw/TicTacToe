import java.awt.event.ActionEvent;
import java.util.ArrayList;
/**
 * This is the Hard game mode. The board will deliberately try to beat the player.
 *
 */
public class HardBoard extends Board {

	/**
	 * Auto-generated by Eclipse
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<int[]> moves; 
	private int targetRow;
	private int targetCol;

	/**
	 * Constructor for HardBoard
	 * @param game The Game that the board is contained in
	 */
	public HardBoard(Game game)
	{
		super(game);
		this.moves = new ArrayList<int[]>();
	}
	/**
	 * Logic for what happens when the player selects a cell.
	 * Make sure the cell is empty, 
	 * makes move, 
	 * passes move to Game, 
	 * initiates AI's turn
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		XOButton obj = (XOButton)(e.getSource());
		int[] coords = this.getButtCoords(Integer.parseInt(e.getActionCommand()));
		
		if(!obj.isFilled()) {
			obj.setIconX();
			this.moves.add(coords);
			game.turn(1, coords[0], coords[1]);
		}
		if(!game.isGameOver()) {
			takeAITurn();
			int i = (3*targetRow)+targetCol;
			butts[i].setIconO();
			game.turn(2, targetRow, targetCol);
		}
	}

	/**
	 * Initiates the AI's turn
	 */
	private void takeAITurn()
	{
		tagWins();
	}
	/**
	 * Checks to see if the AI has any winning moves
	 * @return true when move is completed
	 */
	private boolean tagWins()
	{

		if(checkThrees(true)) return true;
		
		return blockWins();
	}
	/**
	 * Checks to see if AI can block any winning moves
	 * @return true when move is completed
	 */
	private boolean blockWins()
	{
		if(checkThrees(false)) return true;
		return tagCorners();
	}
	/**
	 * Tries to claim a corner piece, nearest the player's last move
	 * @return true when move is completed
	 */
	private boolean tagCorners()
	{
		int[] lastMove = this.moves.get(this.moves.size() -1);
		int[][] corners = { {0,0}, {0,2}, {2,0}, {2,2} };
		boolean isCorner = true;
		for(int i = 0; i < 4; i++)
			if(!java.util.Arrays.equals(corners[i], lastMove)) isCorner = false;
		int targetr = -1;
		int targetc = -1;
		if(!isCorner){
			if(lastMove[0] == 0) targetr = 0;
			else if(lastMove[0] == 2) targetr = 2;
			if(lastMove[1] == 0) targetc = 0;
			else if(lastMove[1] == 2) targetc = 2;
		}
		if(targetr > -1 && targetc > -1) {
			targetRow = targetr;
			targetCol = targetc;
			return true;
		}
		else {
			for(int i = 0; i < 4; i++){
				if(!butts[(3*corners[i][0])+(corners[i][1])].isFilled()){
					targetRow = corners[i][0];
					targetCol = corners[i][1];
					return true;
				}
			}
		}
		return tagEmpty();
	}
	/**
	 * Picks a random empty spot
	 * @return true when move is completed
	 */
	private boolean tagEmpty()
	{
		boolean searching = true;
		int arow, acol;
		while(searching){
    		arow = (int)(Math.random()*3); acol = (int)(Math.random()*3);
    		int i = (3*arow)+acol;
    		if(!butts[i].isFilled()){
    			targetRow = arow;
    			targetCol = acol;
    			searching = false;
    		}//end if
    	}//end while
		return true;
	}

	/**
	 * Checks to see if there is an opportunity for either player to score.
	 * Makes and passes the move to Game
	 * @param winner true will search for AI opportunities to win, false will search for player's opportunity to win
	 * @return true if there is an opportunity for the specified player to score
	 */
	private boolean checkThrees(boolean winner)
	{
		int match, opp;
		if(winner){
			match = 2;
			opp = 1;
		}
		else{
			match = 1;
			opp = 2;
		}
		int check = 0;
		int targetr = -1;
		int targetc = -1;
		
		//checks rows
		for(int row = 0; row < 3 && targetr == -1; row++){
			for(int col = 0; col < 3 && check < 2; col++){
				if(game.gameBoard[row][col] == match) check++;
				else if(game.gameBoard[row][col] == opp) check = 3;
			}
			if(check == 2) targetr = row;
			else check = 0;
		}
		for(int i = 0; i < 3 && targetr > -1; i++)
			if(game.gameBoard[targetr][i] == 0)
				targetc = i;
		if(targetr > -1 && targetc > -1) {
			targetRow = targetr;
			targetCol = targetc;
			return true;
		}
		
		//checks cols
		check = 0;
		targetr = -1;
		targetc = -1;
		for(int col = 0; col < 3 && targetc == -1; col++){
			for(int row = 0; row < 3 && check < 2; row++){
				if(game.gameBoard[row][col] == match) check++;
				else if(game.gameBoard[row][col] == opp) check = 3;
			}
			if(check == 2) targetc = col;
			else check = 0;
		}
		for(int i = 0; i < 3 && targetc > -1; i++)
			if(game.gameBoard[i][targetc] == 0)
				targetr = i;
		if(targetr > -1 && targetc > -1) {
			targetRow = targetr;
			targetCol = targetc;
			return true;
		}
		
		//check diag1
		check = 0;
		int target = -1;
		for(int i = 0; i < 3 && check < 3; i++)
			if(game.gameBoard[i][i] == match) check++;
			else if(game.gameBoard[i][i] == opp) check = 3;
		for(int i = 0; i < 3 && check == 2; i++)
			if(game.gameBoard[i][i] == 0) target = i;
		if(target > -1) {
			targetRow = target;
			targetCol = target;
			return true;
		}
		
		//check diag2
		check = 0;
		target = -1;
		for(int i = 0; i < 3 && check < 3; i++)
			if(game.gameBoard[i][2 - i] == match) check++;
			else if(game.gameBoard[i][2 - i] == opp) check = 3;
		for(int i = 0; i < 3 && check == 2; i++)
			if(game.gameBoard[i][2 - i] == 0) target = i;
		if(target > -1) {
			targetRow = target;
			targetCol = (2 - target);
			return true;
		}
		
		return false;
	}
	/**
	 * Starts over the board and reinits the list of moves
	 */
	protected void startOver()
	{
		super.startOver();
		this.moves = new ArrayList<int[]>();
	}
}
