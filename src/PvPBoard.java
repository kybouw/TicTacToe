import java.awt.event.ActionEvent;

public class PvPBoard extends Board {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PvPBoard(Game game)
	{
		super(game);
	}
	
    public void actionPerformed(ActionEvent e) {
    	XOButton obj = (XOButton)(e.getSource());
    	int[] coords = this.getButtCoords(Integer.parseInt(e.getActionCommand()));
    	
    	turn %= 2;//this sets who's turn it is
		switch(turn){
		case 0:
			if(!obj.isFilled()) {
				obj.setIconX();
				game.turn(turn+1, coords[0], coords[1]);
			}
			break;
		case 1: 
			if(!obj.isFilled()) {
				obj.setIconO();
				game.turn(turn+1, coords[0], coords[1]);
			}
			break;
		}//end switch
		turn++;
    }//end act
}
