/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.comcon;

import frc.robot.commands.vision.ShootWithVision;

/**
 * Add your docs here.
 */
public class Vision {

    public static ShootWithVision shootWithVision = new ShootWithVision();

    public static void startShootWithVision()
    {
        shootWithVision.schedule();
    }
    
    public static void endShootWithVision()
    {
        shootWithVision.cancel();
    }

    public static boolean shooterAtSpeed()
    {
        return shootWithVision.atSpeed;
    }
}
