import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This is the board that is used for play.
 * This class will hold the buttons and basically 
 *  hold everything that the player has to interact with. 
 * It will hold the logic for making moves.
 * 
 * @author Kyle Bouwman
 * @version 0.0.1
 */
public abstract class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private Game game;
	private XOButton[] butts = new XOButton[9];
	protected byte turn;
	
	// instance variables - replace the example below with your own
    public Board(Game game)
    {
    	this.game = game;
		setLayout(new GridLayout(3,3));
		this.turn = 0;
		
		for(int i = 0; i < butts.length; i++){
    		butts[i] = new XOButton();
    		butts[i].setActionCommand("" + i);
    		butts[i].addActionListener(this);
    		add(butts[i]);
    	}
    }//constructor

    public boolean isGameOver()
    {
    	for(int i = 0; i < butts.length; i++)
    		if(!butts[i].isFilled())
    			return false;
    	return true;
    }//end gameover
    
    private int[] getButtCoords(int i)
    {
    	int[] coords = {i/3, i%3};
    	
    	return coords;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	XOButton obj = (XOButton)(e.getSource());
    	int[] coords = this.getButtCoords(Integer.parseInt(e.getActionCommand()));
    	byte row = (byte)(coords[0]), col = (byte)(coords[1]);
    	
    	//TODO endgame
    	if(turn == -1) System.exit(1);
    	
    	
    	turn %= 2;//this sets who's turn it is
    	this.game.turn(turn, row, col);
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
    
    
    
    
}//class
