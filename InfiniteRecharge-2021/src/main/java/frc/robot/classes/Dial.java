package frc.robot.classes;

public class Dial {

    private int value = 0;
    private int max;
    private boolean justTurnedUp = false;
    private boolean justTurnedDown = false;

    public Dial(int positions)
    {
        max = positions - 1;
    }

    public int value() { return value; }

    public int increment()
    {
        value++;
        wrap();
        return value;
    }

    public int decrement()
    {
        value--;
        wrap();
        return value;
    }

    public void set(int newValue)
    {
        value = newValue;
        wrap();
    }

    /**
     * Increments the dial only when 'input' changes to true, 'input' must change to false before the dial will increment again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean incrementWhenTrue(boolean input)
    {
        boolean output = false;
        if (input == true && justTurnedUp == false)
        {
            justTurnedUp = true;
            increment();
            output = true;
        } else if (input == false)
        {
            justTurnedUp = false;
        }
        return output;
    }

    /**
     * Increments the dial only when 'input' changes to true, 'input' must change to false before the dial will increment again.
     * 
     * @return Whether or not the value changed.
     */
    public boolean decrementWhenTrue(boolean input)
    {
        boolean output = false;
        if (input == true && justTurnedDown == false)
        {
            justTurnedDown = true;
            decrement();
            output = true;
        } else if (input == false)
        {
            justTurnedDown = false;
        }
        return output;
    }

    /**
     * Resets the dial back to 0.
     */
    public void resetDial()
    {
        set(0);
    }

    private void wrap()
    {
        value = Equations.wrap(value, 0, max);
    }

    public int getMax()
    {
        return max;
    }
}