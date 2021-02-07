/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.classes.Switch;
import frc.robot.comcon.Intake;
import frc.robot.maps.RobotMap;

public class IntakeSub extends SubsystemBase
{
  private static final VictorSPX runMoter = new VictorSPX(RobotMap.SubsystemMotors.suck.ID());
  private static final TalonSRX moveMoter = new TalonSRX(RobotMap.SubsystemMotors.intake.ID());
  
  public static Switch intakeDeployed = new Switch(false);
  
  static
  {
    moveMoter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
  }
  
  @Override
  public void periodic() { }
  
  public static void intakeControl()
  {
    if (intakeDeployed.flipOnTrue(OI.intakeDR()))
    {
      if (intakeDeployed.state())
      {
        Intake.deploy();
      } else
      {
        Intake.retract();
      }
    }
  }
  
  public static double getPos()
  {
    return moveMoter.getSelectedSensorPosition();
  }
  
  public static void runIntake(double power)
  {
    runMoter.set(VictorSPXControlMode.PercentOutput, power * 0.65);
  }
  
  public static void stopRunning()
  {
    runMoter.set(VictorSPXControlMode.PercentOutput, 0);
  }
  
  public static void moveIntake(double power)
  {
    moveMoter.set(TalonSRXControlMode.PercentOutput, power);
  }
  
  public static void stopMoving()
  {
    moveMoter.set(TalonSRXControlMode.PercentOutput, 0);
  }
}
