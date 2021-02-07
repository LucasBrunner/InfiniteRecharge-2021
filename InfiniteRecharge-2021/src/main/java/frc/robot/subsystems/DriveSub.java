/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.maps.RobotMap.DriveMotors;
import frc.robot.maps.RobotMap.Sensors;

public class DriveSub extends SubsystemBase
{
  // Motors
  private static final TalonSRX lefttMotor1 = new TalonSRX(DriveMotors.leftt1.ID());
  private static final TalonSRX lefttMotor2 = new TalonSRX(DriveMotors.leftt2.ID());
  private static final TalonSRX rightMotor1 = new TalonSRX(DriveMotors.right1.ID());
  private static final TalonSRX rightMotor2 = new TalonSRX(DriveMotors.right2.ID());
  
  // Motor methods
  private static void setMotors(double leftt, double right)
  {
    lefttMotor1.set(TalonSRXControlMode.PercentOutput, -leftt);
    lefttMotor2.set(TalonSRXControlMode.PercentOutput, -leftt);
    rightMotor1.set(TalonSRXControlMode.PercentOutput, right);
    rightMotor2.set(TalonSRXControlMode.PercentOutput, right);
  }
  
  // Sensors
  private static final DutyCycleEncoder lefttEncoder = new DutyCycleEncoder(Sensors.lefttDriveEncoder.Dio1());
  private static final DutyCycleEncoder rightEncoder = new DutyCycleEncoder(Sensors.rightDriveEncoder.Dio1());
  private static final AHRS navX = new AHRS(SPI.Port.kMXP);
  
  static
  {
    lefttEncoder.setDistancePerRotation(25.1);
    rightEncoder.setDistancePerRotation(25.1);
  }
  
  @Override
  public void periodic() { }

  /**
   * Gets the value of the left encoder in rotations.
   * @return Value of the left encoder.
   */
  public static double lefttEncoder()
  {
    return lefttEncoder.get();
  }
  
  /**
   * Gets the value of the right encoder in rotations.
   * @return Value of the right encoder.
   */
  public static double rightEncoder()
  {
    return rightEncoder.get();
  }

  /**
   * Gets the value of the left encoder in inches.
   * @return Value of the left encoder.
   */
  public static double lefttEncoderDistance()
  {
    return rightEncoder.getDistance();
  }

  /**
   * Gets the value of the right encoder in inches.
   * @return Value of the right encoder.
   */
  public static double rightEncoderDistance()
  {
    return rightEncoder.getDistance();
  }
  
  public static double getGyroAngle()
  {
    return navX.getAngle();
  }
  
  public static void resetGyro()
  {
    navX.reset();
  }
  
  /**
   * Arcade-style drivetrain input.
   * @param xPower
   *                    The robot's speed along the X axis [-1.0..1.0]. Forward is
   *                    positive.
   * @param zRotation
   *                    The robot's rotation rate around the Z axis [-1.0..1.0].
   *                    Clockwise is positive.
   */
  public static void arcadeDrive(double xPower, double zRotation)
  {
    double lefttOutput = 0;
    double rightOutput = 0;
    
    double maxInput = Math.copySign(Math.max(Math.abs(xPower), Math.abs(zRotation)), xPower);
    
    if (xPower >= 0.0)
    {
      // First quadrant, else second quadrant
      if (zRotation >= 0.0)
      {
        lefttOutput = maxInput;
        rightOutput = xPower - zRotation;
      } else
      {
        lefttOutput = xPower + zRotation;
        rightOutput = maxInput;
      }
    } else
    {
      // Third quadrant, else fourth quadrant
      if (zRotation >= 0.0)
      {
        lefttOutput = xPower + zRotation;
        rightOutput = maxInput;
      } else
      {
        lefttOutput = maxInput;
        rightOutput = xPower - zRotation;
      }
    }
    
    setMotors(lefttOutput, rightOutput);
  }
  /**
   * Tank-style drivetrain input.
   * @param leftt Speed of the left wheels. Forward is positive.
   * @param right Speed of the right wheels. Forward is positive.
   */
  public static void tankDrive(double leftt, double right)
  {
    setMotors(leftt, right);
  }
  
  public static void stop()
  {
    setMotors(0, 0);
  }
}
