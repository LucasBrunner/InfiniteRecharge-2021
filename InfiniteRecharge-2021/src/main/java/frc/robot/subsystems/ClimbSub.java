/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.classes.Equations;
import frc.robot.classes.Timer;
import frc.robot.comcon.Climber;
import frc.robot.maps.RobotMap;

public class ClimbSub extends SubsystemBase {

  private static final VictorSPX winchMotor = new VictorSPX(RobotMap.SubsystemMotors.winch.ID());
  private static final DigitalInput limitSwitch = new DigitalInput(RobotMap.Sensors.climbLimitSwitch.Dio1());
  private static final Servo release = new Servo(RobotMap.PWMDevices.liftarmRelease.PWM());

  private static Timer timeTillEndgame = new Timer(75000);

  public static void teleopStart()
  {
    timeTillEndgame.reset();
  }

  public static boolean getLimitSwitch()
  {
    return limitSwitch.get();
  }

  public static void deploy()
  {
    release.set(0.2);
  }
  public static void stow()
  {
    release.set(1);
  }

  public static void runClimbMotor(double power)
  {
    winchMotor.set(ControlMode.PercentOutput, -power);
  }

  public static void reverseClimbMotor(double Power)
  {
    Power = Equations.clamp(Power, -1, 0);
    winchMotor.set(ControlMode.PercentOutput, Power);
  }

  public static void stopClimbMotor()
  {
    winchMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    if (timeTillEndgame.done() == true)
    {
      SmartDashboard.putData(Climber.deployLiftarm);
    } else {
      SmartDashboard.delete("Deploy Liftarm");
    }
  }
}
