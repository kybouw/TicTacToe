package players;
import javax.swing.ImageIcon;

import components.Board;

public abstract class Player {

	private ImageIcon icon;
	private Board board;
	
	public Player(Board board, boolean isX)
	{
		this.board = board;
		if(isX) icon = new ImageIcon(this.getClass().getResource("resources/xicon.png"));
		else icon = new ImageIcon(this.getClass().getResource("resources/oicon.png"));
	}
	public void move()
	{
		
	}
}
