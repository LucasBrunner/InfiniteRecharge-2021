/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.commands.intake.Run;
import frc.robot.maps.RobotMap;

public class StorageSub extends SubsystemBase {

  private static final VictorSPX storageMotor = new VictorSPX(RobotMap.SubsystemMotors.storage.ID());
  private static final VictorSPX feederMotor = new VictorSPX(RobotMap.SubsystemMotors.feeder.ID());
  private static final DutyCycleEncoder encoder = new DutyCycleEncoder(RobotMap.Sensors.storageEncoder.Dio1());
  private static final DigitalInput ballDetector = new DigitalInput(RobotMap.Sensors.ballDetector.Dio1());
  public static final DigitalInput storageReset = new DigitalInput(RobotMap.Sensors.storageReset.Dio1());

  private static double offSet = 0.7;

  private static int goal = 0;
  private static boolean goalJustUpdated = false;

  public static void storageControl() // Manual storage control
  {
    int storageDirection = OI.storageManual();
    if (OI.advancedMode() == false) // Check control mode
    {
      StorageSub.updateGoal(storageDirection); // Basic manual controls
    } else {
      StorageSub.updateOffset(storageDirection * 0.05); // Advanced manual controls
    }

    Run.doStorage.flipOnTrue(OI.storageCancel());
  }

  public static boolean getBallDetected() { return !ballDetector.get(); }

  public static void setGoal(int newGoal) { goal = newGoal; }
  public static int getGoal() { return goal; }
  /**
   * will not update again until value changes. For manual control purposes
   * @param amount
   */
  public static void updateGoal(int amount) 
  {
    if (amount != 0 && goalJustUpdated == false)
    {
      goalJustUpdated = true;
      goal += amount;
    } else if (amount == 0)
    {
      goalJustUpdated = false;
    }
  }

  public static void updateOffset(double amount) 
  {
    offSet += amount;
  }

  public static void setFeederMotor(double power)
  {
    feederMotor.set(VictorSPXControlMode.PercentOutput, power);
  }
  
  public static double getEncoderPosition() {
    return encoder.getDistance() - offSet;
  }

  public static double getRawEncoderPosition() {
    return encoder.getDistance();
  }

  public static void storagePower(double power) {
    storageMotor.set(VictorSPXControlMode.PercentOutput, power * 0.25);
  }

  public static void storageStop() {
    storageMotor.set(VictorSPXControlMode.PercentOutput, 0);
  }

  /**
   * Creates a new Storage.
   */
  static {
    encoder.setDistancePerRotation(1);
  }
  

  @Override
  public void periodic() 
  {
    if ( storageReset.get() == false )
    {
      offSet = encoder.get() - (int) encoder.get();
    }

    if (OI.storageCancel() && OI.advancedMode())
    {
      goal = (int) getEncoderPosition();
    }
  }
}
