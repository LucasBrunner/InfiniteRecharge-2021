/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.classes.Timer;
import frc.robot.subsystems.DriveSub;

public class Straight extends CommandBase
{
  private DriveSub driveSub = RobotContainer.driveSub;

  // Sensor zero values
  private double lefttEncoderZero;
  private double rightEncoderZero;
  private double angleZero;

  // Sensor zero methods
  private double lefttEncoder() { return DriveSub.lefttEncoderDistance() - lefttEncoderZero; }
  private double rightEncoder() { return DriveSub.rightEncoderDistance() - rightEncoderZero; }
  private double gyroAngle()    { return DriveSub.getGyroAngle() - angleZero; }

  // PIDControllers
  private PIDController drivePID = new PIDController(1, 0, 0);
  private PIDController turnPID = new PIDController(1, 0, 0);

  // Power values
  private double drive;
  private double turn;

  private Timer settlingTime = new Timer(500);
  
  public Straight(double inchDistance) {
    addRequirements(driveSub);
    drivePID.setSetpoint(inchDistance);
    turnPID.setSetpoint(0);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    lefttEncoderZero = DriveSub.lefttEncoderDistance();
    rightEncoderZero = DriveSub.rightEncoderDistance();
    angleZero = DriveSub.getGyroAngle();
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    drive = (lefttEncoder() + rightEncoder()) / 2;
    drive *= 0.01;
    drive = drivePID.calculate(drive);

    turn = gyroAngle();
    turn *= 0.01;
    turn = turnPID.calculate(turn);

    DriveSub.arcadeDrive(drive, turn);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    boolean output = false;
    if (turn < 0.05 && drive < 0.05 && settlingTime.done())
    {
      output = true;
    } else {
      settlingTime.reset();
    }
    return output;
  }
}
