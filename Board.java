import java.io.IOException;

// Board class controls operation of game board
public class Board
{
    public static char[][] board;
    private boolean hasWon;

    // constructor instantiates the board as 2D array
    public Board(int row, int col)
    {
        Board.board = new char[row][col];
        this.hasWon = false;
    }

    // accessor method for board, returned as a string for generality
    public String boardToString()
    {
        String boardString = "";

        // appends board to string
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == 'r')
                {
					boardString += ("| r ");
				}
                else if(board[i][j] == 'y')
                {
					boardString += ("| y ");
				}
                else
                {
					boardString += ("|   ");
				}
			}
            boardString += ("|");
            boardString += "\n";
        }
        // appends column numbers to string
        boardString += ("  " + 1);
        for(int k = 1; k < board[0].length; k++)
        {
            boardString += ("   " + (k + 1));
        }
        return boardString;
    }
    
    // method for placing counter
    // takes player colour and column as arguments, but also other player's colour for generality when checking if square is occupied
    public boolean placeCounter(char player, int position, char otherPlayer){
        boolean placed = false;
        
        for(int i=board.length-1; i>=0; i--)
        {
            if(!placed)
            {
                if(board[i][position-1] == otherPlayer)
                {
                    // skip
                }
                else if(board[i][position-1] != player)
                {
                    board[i][position-1] = player;
                    placed = true;
                }

                // checks if column selected is full
                if(i == 0 && !placed)
                {
                    System.out.println("Column is full");
                }
            }
        }

        return placed;
    }
    
    // method checks board horizontally for win (4 consecutive counters)
    public void checkHorizontal(char counter)
    {
        int count = 0;

        // cycle through rows
        for(int i=0; i<board.length; i++)
        {
            // cycle through columns
            for(int j=0; j<board[i].length; j++)
            {
                if(board[i][j] == counter)
                {
                    count = count + 1;
                    if(count >= 4)
                    {
                        hasWon = true;
                    }
                }
                else
                {
                    count = 0;
                }
            }
            count = 0;
        }
    }

    // method checks board vertically for win
    public void checkVertical(char counter)
    {
        int count = 0;

        // cycle through columns
        for(int i=0; i<board[0].length; i++)
        {
            // cycle through rows
            for(int j=0; j<board.length; j++)
            {
                if(board[j][i] == counter){
                    count = count + 1;
                    if(count >= 4)
                    {
                        hasWon = true;
                    }
                }
                else
                {
                    count = 0;
                }
            }
            count = 0;
        }
    }

    // method checks board along leading diagonal for win
    public void checkLeadingDiagonal(char counter)
    {
        int count = 0;

        // start by cycling through columns staring with each row from bottom to top
        int startRow = board.length - 1;
        do
        {
            int row = startRow;
            int startCol = 0;
            do
            {
                try
                {
                    if(board[row][startCol] == counter)
                    {
                        count = count + 1;
                        if(count >= 4)
                        {
                            hasWon = true;
                        }
                    }
                    else
                    {
                        count = 0;
                    }
                }
                // if index out of bounds, reset startCol so that it exits do loop
                catch(Exception e)
                {
                    startCol = board[0].length;
                }
                finally
                {
                    row++;
                    startCol++;
                }

            } while(startCol < board[0].length);
            startRow--;
            count = 0;
        } while(startRow > 0);

        count = 0;
        // now cycle through rows staring with each column from beginning to end
        int startCol = 0;
        do
        {
            int col = startCol;
            startRow = 0;
            do
            {
                try
                {
                    if(board[startRow][col] == counter)
                    {
                        count = count + 1;
                        if(count >= 4)
                        {
                            hasWon = true;
                        }
                    }
                    else
                    {
                        count = 0;
                    }
                }
                catch(Exception e)
                {
                    startRow = board[0].length;
                }
                finally
                {
                    startRow++;
                    col++;
                }

            } while(startRow < board.length);
            startCol++;
            count = 0;
        } while(startCol < board[0].length);
    }

    // method checks board along reverse diagonal for win
    public void checkReverseDiagonal(char counter)
    {
        int count = 0;

        // start by cycling through columns staring with each row from bottom to top
        int startRow = board.length - 1;
        do
        {
            int row = startRow;
            int startCol = board[0].length - 1;
            do
            {
                try
                {
                    if(board[row][startCol] == counter)
                    {
                        count = count + 1;
                        if(count >= 4)
                        {
                            hasWon = true;
                        }
                    }
                    else
                    {
                        count = 0;
                    }
                }
                catch(Exception e)
                {
                    startCol = 0;
                }
                finally
                {
                    row++;
                    startCol--;
                }

            } while(startCol > 0);
            startRow--;
            count = 0;
        } while(startRow > 0);

        count = 0;
        // now cycle through rows staring with each column from end to beginning
        int startCol = board[0].length - 1;
        do
        {
            int col = startCol;
            startRow = 0;
            do
            {
                try
                {
                    if(board[startRow][col] == counter)
                    {
                        count = count + 1;
                        if(count >= 4)
                        {
                            hasWon = true;
                        }
                    }
                    else
                    {
                        count = 0;
                    }
                }
                catch(Exception e)
                {
                    startRow = board[0].length;
                }
                finally
                {
                    startRow++;
                    col--;
                }

            } while(startRow < board.length);
            startCol--;
            count = 0;
        } while(startCol >= 0);
    }

    // accessor method for returning hasWon boolean
    public boolean getHasWon()
    {
        return hasWon;
    }

    // modifier method for returning hasWon boolean
    public void setHasWon(boolean haveWon)
    {
        hasWon = haveWon;
    }
}
