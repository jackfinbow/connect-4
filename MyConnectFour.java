import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// MyConnectFour class sets up the game and runs methods to play the game
public class MyConnectFour
{	
	private BufferedReader input;
	private ArrayList<Player> players;
	private int currentPlayer;
	private Board board;
	private View view;

	// constructor instantiates BufferedReader for player to select counter colour and board size
	// also sets up the game and run the game
	public MyConnectFour()
	{
		input = new BufferedReader(new InputStreamReader(System.in));
		setUpGame();
		playGame();
	}

	// method sets up the game
	// assigns players their counter colour and creates the game board
	private void setUpGame()
	{
		introMessage();

		assignPlayers();

		createBoard();
	}
	
	// method places the game until a player wins
	private void playGame()
	{
		boolean win = false;
		while(!win)
		{
			boolean isCounterPlaced = false;
			// keep asking player (or computer) for input until counter is placed
			// counter won't be placed if column selected is already full
			while(!isCounterPlaced)
			{
				view.display("Enter the column you'd like to place your counter in:");
				// obtain input from current player
				int move = players.get(currentPlayer).getInput();
				// place counter for current player with their counter colour
				isCounterPlaced = board.placeCounter(players.get(currentPlayer).getCounter(), move, players.get((currentPlayer + 1) % players.size()).getCounter());
			}
			
			// check for win (4 consecutive counters)
			board.checkHorizontal(players.get(currentPlayer).getCounter());
			board.checkVertical(players.get(currentPlayer).getCounter());
			board.checkLeadingDiagonal(players.get(currentPlayer).getCounter());
			board.checkReverseDiagonal(players.get(currentPlayer).getCounter());
			
			// prints the board to command line console
			view.display(board.boardToString());
			// if current player has won, print message stating the player that has won
			if(board.getHasWon()){
				win = true;
				view.display(players.get(currentPlayer).getClass().getName() + " has Won!!!");	//https://stackoverflow.com/questions/6271417/java-get-the-current-class-name
			}

			board.setHasWon(false);
			// switch current player
			currentPlayer = (currentPlayer + 1) % players.size();
		}
	}

	// method prints introduction message, explaining rules of game
	private void introMessage()
	{
		view = new CommandLineView();

		view.display("Welcome to Connect 4");
		view.display("To play the game type in the number of the column you want to drop you counter in");
		view.display("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
		view.display("A player plays against the computer");
		view.display("There are 2 colours, red and yellow");
		view.display("Would you like to be red or yellow? (r/y)");
	}

	// method assigns each player with a counter colour
	private void assignPlayers()
	{
		// list of colours (red and yellow)
		ArrayList<Character> colours = new ArrayList<Character>();
		colours.add('r');
		colours.add('y');
		char colour ='\0';	//https://stackoverflow.com/questions/5859934/char-initial-value-in-java
		boolean isROrY = false;		// boolean that is true if colour is r or y
		// keep asking for player for colour if r or y not picked
		// or print error message if char not inputted
		try
		{
			while(isROrY == false)
			{
				colour = input.readLine().charAt(0);
				if(colour == 'r' || colour == 'y')
				{
					isROrY = true;
				}
				else
				{
					view.display("The colour you select must be r or y. Please re-enter:");
				}
			}
		}
		catch(Exception e)
		{
			view.display("An input was not detected");
		}
		int colourIndex = colours.indexOf(colour);
		// create instance of Human for player
		Human player1 = new Human(colour);

		// create instance of Computer for computer player, with counter as alternative colour from list
		char compColour = colours.get((colourIndex + 1) % colours.size());
		Computer computer = new Computer(compColour);

		view.display("Player is " + player1.getCounter());
		view.display("Computer is " + computer.getCounter());

		// creates list of players
		// cycling through allows selection of current player
		players = new ArrayList<Player>();
		players.add(player1);
		players.add(computer);
		currentPlayer = 0;
	}

	// method creates the game board from player input of number of rows and columns
	private void createBoard()
	{
		view.display("");
		view.display("Enter the number of rows for the Connect 4 grid:");
		int numRows = players.get(0).getInput();
		view.display("");
		view.display("Enter the number of columns for the Connect 4 grid:");
		int numCols = players.get(0).getInput();
		board = new Board(numRows, numCols);
		view.display(board.boardToString());
	}
}