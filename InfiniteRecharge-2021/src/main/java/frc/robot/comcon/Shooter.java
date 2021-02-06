/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.comcon;

import frc.robot.commands.shooter.EmptyStorage;
import frc.robot.commands.vision.AutoShooter;

/**
 * Add your docs here.
 */
public class Shooter {
    private static final AutoShooter autoShooter = new AutoShooter();
    private static final EmptyStorage emptyStorage = new EmptyStorage();

    public static void toggleAutoShooter()
    {
        if (autoShooter.isScheduled() == false)
        {
            autoShooter.schedule();
        } else {
            autoShooter.cancel();
        }
    }

    public static void toggleEmptyStorage()
    {
        if (emptyStorage.isScheduled())
        {
            emptyStorage.cancel();
        } else {
            emptyStorage.schedule();
        }
    }
}
