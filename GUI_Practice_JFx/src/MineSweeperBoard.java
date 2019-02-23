
public class MineSweeperBoard {
	private int[][] _board;
	private int _mines;
	private int _largestXnum = 50;
	private int _largestYnum = 50;
	protected final int mine = 9;
	protected final int error = -1;
	
	// Default Board 
	MineSweeperBoard() {
		_board = new int[10][10];
		_mines = 20;
		generateBoard();
	}
	
	MineSweeperBoard(int x, int y, int mines) {	
		// TODO
		// I should add a check to make sure that there aren't ever more mines passed in than there are spaces in the mine field.
		// I should also define a maximum size for game board 50 * 50 fills my current screen. I will have that as basic limit for now
		
		if(x > _largestXnum) {
			x = _largestXnum;
		}
		if(x < 1) {
			x = 1;
		}
		if(y > _largestYnum) {
			x = _largestYnum;
		}
		if(y < 1) {
			x = 1;
		}
		// Check that there are enough spaces to fit the requested mines.
		if(mines > x * y) {
			// Max size is x * y
			mines = (x * y - x);
		}

		_board = new int[x][y];
		this._mines = mines;
		generateBoard();
	}
	
	public int getPos(int x, int y) {
		// test that we aren't out of our grid?
		if(x >= 0 && y >= 0 && x < _board.length && y < _board[0].length) {
			return _board[x][y];
		}
		else return error;
	}
	
	public int getMineCount() {
		return this._mines;
	}
	
	private void generateBoard() {
		placeMines();
		placeDangerTokens();
	}

	private void placeMines() {
		int count = this._mines;
		boolean finished = false;
		
		while(!finished) {
			for(int i=0; i<this._board.length; i++) {
				for(int j=0; j<this._board[i].length; j++) {
					// If there isn't a mine here already
					if(this._board[i][j] != mine && count > 0) {
						// Let chance decide if we place a mine or not.
						if((Math.random() * 100) > 95) {
							count--;
							_board[i][j] = mine;
						}
						if(count == 0) { // If we have placed all mines.
							finished = true;
						}
					}				
				}
			}
		}
	}
	
	private void placeDangerTokens() {
		for(int i=0; i<this._board.length;i++) {
			for(int j=0 ;j<this._board[i].length ;j++) {
				if(this._board[i][j] != mine) {
				calculateDanger(i,j);
				}
			}	
		}	
	}
	
	private void calculateDanger(int x, int y) {
		//   y-->
		// x
		// |
		// V
		
		// test adjacent cells
		if(x != 0) {
			if(_board[x - 1][y] == mine)
				_board[x][y]++;
		}
		if(y != 0) {
			if(_board[x][y - 1] == mine)
				_board[x][y]++;
		}
		if(x != _board.length - 1) {
			if(_board[x + 1][y] == mine)
				_board[x][y]++;
		}
		if(y != _board[x].length -1) {
			if(_board[x][y + 1] == mine)
				_board[x][y]++;
		}
		
		// now test corners
		if(x != 0 && y != 0) {
			if(_board[x - 1][y - 1] == mine)
				_board[x][y]++;
		}
		if(x != 0 && y != _board[x].length -1) {
			if(_board[x - 1][y + 1] == mine)
				_board[x][y]++;
		}
		if(x != _board.length - 1 && y != 0) {
			if(_board[x + 1][y - 1] == mine)
				_board[x][y]++;
		}
		if(x != _board.length - 1 && y != _board[x].length -1) {
			if(_board[x + 1][y + 1] == mine)
				_board[x][y]++;
		}
	}
	
	public void printMineField() {
		for(int i=0; i<this._board.length; i++) {
			for(int j=0; j<this._board[i].length; j++) {
				System.out.print("|" + this._board[i][j] + "|");
			}	
			System.out.println();
		}
	}
	
}
