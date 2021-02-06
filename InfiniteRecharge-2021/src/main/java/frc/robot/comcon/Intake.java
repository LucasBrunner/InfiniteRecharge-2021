/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.comcon;

import frc.robot.commands.intake.Deploy;
import frc.robot.commands.intake.Retract;
import frc.robot.commands.intake.Run;

/**
 * Add your docs here.
 */
public class Intake
{
    public static Deploy deployIntake = new Deploy();
    private static Retract retractIntake = new Retract();
    public static Run runIntake = new Run();

    public static void deploy()
    {
        deployIntake.schedule();
    }

    public static void retract()
    {
        retractIntake.schedule();
    }

    public static void run()
    {
        runIntake.schedule();
    }

    public static void stop()
    {
        runIntake.cancel();
    }
}
