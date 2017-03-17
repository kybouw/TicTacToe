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
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	XOButton obj = (XOButton)(e.getSource());
    	int[] coords = this.getButtCoords(Integer.parseInt(e.getActionCommand()));
    	byte row = (byte)(coords[0]), col = (byte)(coords[1]);
    	
    	//TODO endgame
    	if(turn == -1) System.exit(1);
    	
    	turn %= 2;//this sets who's turn it is
		switch(turn){
		case 0:
			if(!obj.isFilled()) {
				obj.setIconX();
				if(!game.turn(turn+1, row, col))turn++;
				else turn = -1;
			}
			break;
		case 1: 
			if(!obj.isFilled()) {
				obj.setIconO();
				if(!game.turn(turn+1, row, col))turn++;
				else turn = -1;
			}
			break;
		}//end switch
    }//end act
}
