import javafx.scene.control.ToggleButton;

public class MinesweeperToggleButton extends ToggleButton {
	
	private int _x, _y;
	
	MinesweeperToggleButton (String label, int x, int y) {
		super(label);
		_x = x;
		_y = y;
	}
	
	public int getX() {
		return this._x;
	}
	
	public int getY() {
		return this._y;
	}
}
