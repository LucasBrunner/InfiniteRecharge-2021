/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.maps.ControllerMap;
import frc.robot.maps.RobotMap;

/**
 * Add your docs here.
 */
public class OI 
{
    private static final Joystick m_Joy1 = new Joystick(RobotMap.mainJoy);

    public static boolean advancedMode() { return m_Joy1.getRawButton(ControllerMap.Storage.advMode.ID()); }

    public static boolean bButton() { return m_Joy1.getRawButton(2); }
    public static boolean aButton() { return m_Joy1.getRawButton(1); }

    public static boolean startShooter() { return m_Joy1.getRawButtonPressed(ControllerMap.Shooter.Start.ID()); }
    public static boolean switchLights() { return m_Joy1.getRawButtonPressed(ControllerMap.Shooter.Lights.ID()); }

    public static double driveYAxis() { return m_Joy1.getRawAxis(ControllerMap.Drivebase.yAxis.ID()); }
    public static double drivezAxis() { return -m_Joy1.getRawAxis(ControllerMap.Drivebase.zAxis.ID()); }
    public static double drivexAxis() { return m_Joy1.getRawAxis(0); }

    public static double  intakePower() { return triggersAsJoy(ControllerMap.Intake.throttle1.ID(), ControllerMap.Intake.throttle2.ID(), m_Joy1); }
    public static boolean intakeDR()    { return m_Joy1.getRawButton(ControllerMap.Intake.updown.ID()); }

    public static boolean storageCancel() { return m_Joy1.getRawButton(ControllerMap.Storage.cancel.ID()); }
    public static int storageManual()
    {
        int output;
        switch (m_Joy1.getPOV())
        {
            case 90:
                output = -1;
                break;
            case 270:
                output = 1;
                break;
            default:
                output = 0;
                break;
        }
        return output;
    }
    
    public static double climbPower()  { return triggersAsJoy(ControllerMap.Climber.throttleUp.ID(), ControllerMap.Intake.throttle2.ID(), m_Joy1); }
    public static boolean deployLifer() { return m_Joy1.getRawButtonPressed(ControllerMap.Climber.deploy.ID()); }

    /**
     * Returns a -1 to 1 range based of the position of the left and right triggers. 
     * 
     * @param controller
     * 
     * @return If the left trigger is down: left trigger value, if the right trigger is down: right trigger value, else 0.
     */
    private static double triggersAsJoy(int axisID1, int axisID2, Joystick joy) {
        double axis1 = joy.getRawAxis(axisID1);
        double axis2 = joy.getRawAxis(axisID2);
        double output = 0;
        if (axis1 == 0 && axis2 == 0) 
        {
        } else if (axis1 > 0) {
            output = -axis1;
        } else if (axis2 > 0) {
            output = axis2;
        }
        return output;
    }
}
