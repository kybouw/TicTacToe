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
 * @version 0.0.1
 */
public abstract class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected Game game;
	protected XOButton[] butts = new XOButton[9];
	protected int turn;
	
	// instance variables - replace the example below with your own
    public Board(Game game)
    {
    	this.game = game;
		setLayout(new GridLayout(3,3));
		for(int i = 0; i < butts.length; i++){
    		butts[i] = new XOButton();
    		butts[i].setActionCommand("" + i);
    		add(butts[i]);
    	}
		startGame();
    }//constructor
    
    protected int[] getButtCoords(int i)
    {
    	int[] coords = {i/3, i%3};
    	return coords;
    }
    
    protected void startOver()
    {
    	for(int i = 0; i < butts.length; i++)
    		butts[i].setIconClear();
    	startGame();
    }
    
    private void startGame()
    {
    	for(int i = 0; i < butts.length; i++)
    		butts[i].addActionListener(this);
    	this.turn = 0;
    }
    protected void endGame()
    {
    	for(int i = 0; i < butts.length; i++)
    		butts[i].removeActionListener(this);
    }

    public abstract void actionPerformed(ActionEvent e);
    
}//class
