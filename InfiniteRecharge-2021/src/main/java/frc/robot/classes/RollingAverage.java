package frc.robot.classes;

import java.util.ArrayList;

public class RollingAverage
{
    private double[] rollingValues;
    private int milliSecondsPerSample = 100;
    private long nextSampleTime = System.currentTimeMillis() + milliSecondsPerSample;

    private static ArrayList<RollingAverage> allAverages = new ArrayList<>();

    public RollingAverage(double rollingTime)
    {
        allAverages.add(this);
        rollingValues = new double[(int) (10 * rollingTime)];
    }

    public static int InstanceCount()
    {
        return allAverages.size();
    }

    public static void removeInstance(RollingAverage toRemove)
    {
        allAverages.remove(toRemove);
        toRemove = null;
    }

    public void addSample(double sample)
    {
        if (System.currentTimeMillis() > nextSampleTime)
        {
            rollNewSample(sample);
            nextSampleTime += milliSecondsPerSample;
        }
    }

    private void rollNewSample(double sample)
    {
        if (Equations.closestToZero(sample, rollingValues[0]))
        {
            fillWithValue(sample);
        } else {
            for (int i = rollingValues.length - 1; i > 0; i--) {
                rollingValues[i] = rollingValues[i - 1]; 
            }
            rollingValues[0] = sample;
        }
    }

    private void fillWithValue(double value)
    {
        for (int i = 0; i < rollingValues.length; i++) {
            rollingValues[i] = value;
        }
    }

    public double getAverage()
    {
        return Equations.arraySum(rollingValues) / rollingValues.length;
    }
}