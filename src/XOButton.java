import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This is the XOButton Object, a child of JButton. 
 * 
 * @version 0.0.1
 * 
 *
 */
public class XOButton extends JButton {

	private static final long serialVersionUID = 1L;
	private final ImageIcon X_ICON = new ImageIcon(this.getClass().getResource("xicon.png"));
	private final ImageIcon O_ICON = new ImageIcon(this.getClass().getResource("oicon.png"));
	private boolean isFilled;

	public XOButton() {
		super();
		this.isFilled = false;
	}
	public void setIconX()
	{
		setIcon(X_ICON);
		this.isFilled = true;
	}
	public void setIconO()
	{
		setIcon(O_ICON);
		this.isFilled = true;
	}
	public boolean isFilled()
	{
		return this.isFilled;
	}
}//end class
