import java.awt.event.ActionEvent;

import components.Board;
import components.Game;

/**
 * This is a Player versus Player Board model. There are two players, X and O, X goes first. 
 * This contains how the move/turn system works and how the player(s) interacts with the game.
 *
 */
public class PvPBoard extends Board {

	/**
	 * Auto-generated by Eclipse
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Constructor for PvPBoard
	 * @param game the Game object that the board is contained in
	 */
	public PvPBoard(Game game)
	{
		super(game);
	}
	/**
	 * Logic for what happens when a button is pressed.
	 * Determine who's turn it is, 
	 * make sure the spot is empty, 
	 * place an icon there, 
	 * passes the move to the Game for analysis, 
	 * rotate turns.
	 */
    public void actionPerformed(ActionEvent e) {
    	XOButton obj = (XOButton)(e.getSource());
    	int[] coords = this.getButtCoords(Integer.parseInt(e.getActionCommand()));
    	
    	turn %= 2;//this sets who's turn it is
		switch(turn){
		case 0:
			if(!obj.isFilled()) {
				obj.setIconX();
				game.turn(turn+1, coords[0], coords[1]);
			}else return;
			break;
		case 1: 
			if(!obj.isFilled()) {
				obj.setIconO();
				game.turn(turn+1, coords[0], coords[1]);
			}else return;
			break;
		}//end switch
		turn++;
    }//end act
}
