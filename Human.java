import java.io.BufferedReader;
import java.io.InputStreamReader;

// Human class inherits Player and counter set using Player constructor
// constructor here instantiates BufferedReader for user input
// and contains implementation of input method by reading input from console
public class Human extends Player
{
    private BufferedReader input;

    public Human(char counter)
    {
        super(counter);
        this.input = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public int getInput()
    {
        String userInput = null;
        int intInput = 0;
        
        try
        {			
            userInput = input.readLine();
            intInput = Integer.parseInt(userInput);
		}
        catch(Exception e)
        {
			System.out.println("An input was not detected or input wasn't an integer");
        }
        
		return intInput;
    }
}
