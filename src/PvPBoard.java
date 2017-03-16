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
    	if(turn == -1) System.exit(1);
    	turn %= 2;
		switch(turn){
		case 0:
			if(!obj.isFilled()) {
				obj.setIconX();
				if(!this.isGameOver())turn++;
				else turn = -1;
			}
			break;
		case 1: 
			if(!obj.isFilled()) {
				obj.setIconO();
				if(!this.isGameOver())turn++;
				else turn = -1;
			}
			break;
		}//end switch
    }//end act
}
