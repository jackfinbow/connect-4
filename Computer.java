import java.util.Random;

// Computer class inherits Player and counter set using Player constructor
// and contains implementation of input method by generating random integer between column range
public class Computer extends Player
{
    public Computer(char counter)
    {
        super(counter);
    }

    @Override
    public int getInput()
    {
        int intInput = 0;
        Random rand = new Random();

        intInput = rand.nextInt(Board.board[0].length) + 1;
        return intInput;
    }
}
