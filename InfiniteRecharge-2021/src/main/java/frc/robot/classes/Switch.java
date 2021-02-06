package frc.robot.classes;

public class Switch {

    private boolean state;
    private boolean justChanged;

    /**
     * Creates a new switch with an initial state of true.
     */
    public Switch()
    {
        state = true;
    }

    public Switch(boolean initialState)
    {
        state = initialState;
    }

    public boolean state()
    {
        return state;
    }

    public void flip()
    {
        state = !state;
    }

    /**
     * Flips the switch only when 'input' changes to true, 'input' must change to false before the switch will flip again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean flipOnTrue(boolean input)
    {
        boolean output = false;
        if (input == true && justChanged == false)
        {
            justChanged = true;
            flip();
            output = true;
        } else if (input == false)
        {
            justChanged = false;
        }
        return output;
    }

    public void set(boolean newState)
    {
        state = newState;
    }
}