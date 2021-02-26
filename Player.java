// abstract class Player contains counter variable, set in constructor,
// accessor method for it, and abstract declaration for player input without method body
public abstract class Player
{
    private char counter;

    public Player(char counter)
    {
        this.counter = counter;
    }

    abstract int getInput();
    
    public char getCounter()
    {
        return counter;
    }
}
