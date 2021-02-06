/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.comcon;

import frc.robot.commands.drivebase.OperatorDrive;

/**
 * Add your docs here.
 */
public class Drive
{
    public static final OperatorDrive operatorDrive = new OperatorDrive();

    public static void OperatorControl()
    {
        operatorDrive.schedule();
    }
}
