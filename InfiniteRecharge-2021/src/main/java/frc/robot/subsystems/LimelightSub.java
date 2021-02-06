/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSub extends SubsystemBase {

  private static boolean limelightOn = false;

  /**
   * Creates a new LimelightSub.
   */
  public LimelightSub() {

  }

  public static double getVerOffset()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public static double getHorOffset()
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public static boolean switchLEDs()
  {
    limelightOn = !limelightOn;
    return limelightOn;
  }

  public static boolean switchLEDs(boolean state)
  {
    limelightOn = state;
    return limelightOn;
  }

  @Override
  public void periodic() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(limelightOn ? 0 : 1);
  }
}
