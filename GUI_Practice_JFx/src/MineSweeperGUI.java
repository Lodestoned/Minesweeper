import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
//import javafx.scene.Scene;

@SuppressWarnings("rawtypes")
public class MineSweeperGUI extends Application implements EventHandler {

	ToggleButton[][] buttons;
	MineSweeperBoard mineField;
	// HBox bottomBar;
	Label bombsToFind;
	
	String gameLossOutput = "Boom!  You are dead.";
	// String gameInProgressOutput = ""; // Searching || Mines left || etc...  
	String gameWinOutput = "Congratulations! You Win";
	
	// Button format 
	String blankLabel = "   ";
	String flagLabel = "|*";
	
	// Default settings.
	int x = 10;
	int y = 10;	
	int numOfMines = 20;
	
	// Game scoring values. 
	int flagsPlaced = 0;
	int cellsNotSelected = x * y;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO
		// - Must ask for the x y parameters and number of bombs from user.
		// - Must make a restart game button.
		
		
		buttons = new MinesweeperToggleButton[x][y];
				
		int i;
		int j;
		
		for(i=0; i<x; i++){
			for(j=0; j<y;j++){
				buttons[i][j] = new MinesweeperToggleButton(blankLabel, i, j);
				buttons[i][j].setOnMouseClicked(this);
			}
		}
	
		
		// SetUp the MineField 		
		mineField = new MineSweeperBoard(x, y, numOfMines);
		
		
		// Set up the Grid
		GridPane paneG = new GridPane();
		
		for(i=0; i<x; i++){
			for(j=0; j<y;j++){
				paneG.add(buttons[i][j] , i, j);
			}
		}
		
		
		// Set up options bar and current score bar.
		// TODO
		HBox topBar = new HBox();
		topBar.getChildren().addAll(new Label("Options"));
		
		HBox bottomBar = new HBox();
		bombsToFind = new Label("Bombs left: ");
		bottomBar.getChildren().addAll(bombsToFind);		
		// bottomBar.setPadding(new Insets(10));
		
		BorderPane paneB = new BorderPane();
		paneB.setPadding(new Insets(10));
		paneB.setCenter(paneG);
		// paneB.setTop(topBar);
		paneB.setBottom(bottomBar);
		Scene scene = new Scene(paneB);
		
		primaryStage.setTitle("Mine Sweeper (MINE)");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	@Override
	public void handle(Event e) {
  
		Object __e = e.getSource();
		
		
		if(e.getSource() instanceof MinesweeperToggleButton && e instanceof MouseEvent) {
			
			MinesweeperToggleButton _e = (MinesweeperToggleButton) __e;	
			
			// If right clicked icon becomes flagged or non-flagged
			// If location has already been selected then right click is ignored.
			if(((MouseEvent) e).getButton() == MouseButton.SECONDARY ) {
				
				if(_e.getText().equals(blankLabel)) {
					flagsPlaced++;
					cellsNotSelected--;
					_e.setText(flagLabel);
					_e.setSelected(false);
				}
				else if(_e.getText().equals(flagLabel)) {
					flagsPlaced--;
					cellsNotSelected++;
					_e.setText(blankLabel);
					_e.setSelected(false);	
				}				
				return;
			}
			
			// If we click and location has no flag on it.
			if(_e.isSelected() && _e.getText().equals(flagLabel) == false ) {
				
				cellsNotSelected--;
				
				// If we have clicked on a mine. 
				if(mineField.getPos(_e.getX(), _e.getY()) == mineField.mine) {
					// Loss condition
					bombsToFind.setText(gameLossOutput);
				}
				
				_e.setText("" + mineField.getPos(_e.getX(), _e.getY()) + " ");
				
				// mineField.getPos(_e.getX(), _e.getY())
				
				
			}
       	 	else if (_e.isSelected() == false && _e.getText().equals(flagLabel) == false) {
       	 		_e.setSelected(true);	 		
       	 	}
       	 	else {
       	 		_e.setSelected(false);	
       	 	}
		} 
	}

	
	public static void main(String[] args) 
	{
		launch(args);
	}	
	
}
