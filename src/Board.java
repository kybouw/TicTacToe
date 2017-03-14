import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This is the board that is used for play.
 * 
 * @author Kyle Bouwman
 * @version 0.0.1
 */
public class Board extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private XOButton[] butts = new XOButton[9];
	private JPanel boardPanel = new JPanel();
	private JPanel scorePanel = new JPanel();
	private byte turn;
	private boolean isGameOver;

	// instance variables - replace the example below with your own
    public static void main(String[] args) {
        new Board().setVisible(true);
    }//main
    public Board(){
    	super("TicTacToe");
    	this.turn = 0;
    	this.isGameOver = false;
    	setSize(600,600);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	boardPanel.setLayout(new GridLayout(3,3));
    	
    	for(int i = 0; i < butts.length; i++){
    		butts[i] = new XOButton();
    		butts[i].setActionCommand("click");
    		butts[i].addActionListener(this);
    		boardPanel.add(butts[i]);
    	}
    	add(boardPanel);
    	
    	JLabel text = new JLabel("This is my text");
    	scorePanel.add(text);
    	add(scorePanel, BorderLayout.SOUTH);
    	
    	
    }//constructor
    
    private boolean isGameOver()
    {
    	for(int i = 0; i < butts.length; i++){
    		if(!butts[i].isFilled()){
    			this.isGameOver = false;
    			return this.isGameOver;
    		}
    	}
    	this.isGameOver = true;
    	return this.isGameOver;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	XOButton obj = (XOButton) ((AbstractButton) e.getSource());
    	if(turn == -1) System.exit(1);
    	turn %= 2;
		switch(turn){
		case 0:
			obj.setIconX();
			break;
		case 1: 
			obj.setIconO();
			break;
		}//end switch
		if(!this.isGameOver()){
			turn++;
		}//end if
		else {
			turn = -1;
		}
    	
    }//end act
}//class
