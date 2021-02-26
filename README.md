# Connect 4
Java code that allows a human to play Connect 4 against the computer.

## How to play
When starting the game, type 'r' or 'y' to select red or yellow counters. Then specify the size of the Connect 4 grid by typing the desired number of rows and columns.

When it is your turn to play, enter a number between 1 and the number of columns to select which column to place your counter in; if a column is already full, then it will be rejected and you'll be pr0mpted to re-enter a number.

The game ends when either play succeeds in getting 4 counter in the same row, column or diagonal.

## Class structure
* `Main` - creates an instance of the game class.
* `MyConnectFour` - sets up the game by creating the board and assigning players, and controls the playing of the game.
* `Board` - contains methods that control the board, such as placing a counter, checking a player has won, and outputting the board for display.
* `Player` - abstract class containing the colour of the players' counters, and an abstract method for getting the desired column for a given move.
* `Human` - extends `Player` by obtaining user input from the command line for their move.
* `Computer` - extends `Player` by generating a random number between 1 and the number of columns for their move. This is currently completely random, but a future improvement could be to introduce a form of AI for the computer by implementing search algorithms, for example.
* `View` - interface with method for displaying the board for the user.
* `CommandLineView` - implements `View` by printing the board the command line.
