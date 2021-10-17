/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.classes.Equations;
import frc.robot.classes.Timer;
import frc.robot.commands.intake.Run;
import frc.robot.commands.storage.EmptyThroughShooter;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.ShooterSub;

public class ShootWithVision extends CommandBase 
{
  public boolean atSpeed = false;
  private Timer atSpeedTimer = new Timer(100);
  private Timer speedUpTimer = new Timer(2000);

  private PIDController shooterPID = new PIDController(0.00045, 0.001, 0);

  /**
   * Creates a new ShootWithVision.
   */
  public ShootWithVision() {
    addRequirements(RobotContainer.shooterSub);

    if (Robot.testMode)
    {
      SmartDashboard.putNumber("Shooter PID - P", 0.00045);
      SmartDashboard.putNumber("Shooter PID - I", 0.010);
      SmartDashboard.putNumber("Shooter PID - D", 0.000);
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    atSpeed = false;
    speedUpTimer.reset();
    atSpeedTimer.reset();
    
    shooterPID.setTolerance(5, 5);
    shooterPID.setIntegratorRange(-0.1, 0.1);
    EmptyThroughShooter.resumeStorage = Run.doStorage.state();
    
    Run.doStorage.set(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    

    // shooterPID.setP(SmartDashboard.getNumber("Shooter PID - P", 0.00045));
    // shooterPID.setI(SmartDashboard.getNumber("Shooter PID - I", 0.0010));
    // shooterPID.setD(SmartDashboard.getNumber("Shooter PID - D", 0.000));

    double yValue = LimelightSub.getVerOffset();
    double GoalRPM = 0;
    double feedForward = 0;
         if (yValue > 20) { GoalRPM = 2255; feedForward = 0.395; }
    else if (yValue > 15) { GoalRPM = 2175; feedForward = 0.387; }
    else if (yValue > 10) { GoalRPM = 2185; feedForward = 0.390; }
    else if (yValue > 05) { GoalRPM = 2195; feedForward = 0.395; }
    else if (yValue > 00) { GoalRPM = 2260; feedForward = 0.395; }
    GoalRPM += -15;
    shooterPID.setSetpoint(GoalRPM);
    
    double t = shooterPID.calculate(ShooterSub.getVelocity());
    t = Equations.clamp(t, -0.1, 0.1);
    double power = t + feedForward;
    ShooterSub.spin(power);

    SmartDashboard.putNumber("Goal RPM", GoalRPM);
    SmartDashboard.putNumber("Current RPM", ShooterSub.getVelocity());

    if (shooterPID.atSetpoint() != true)
    {
      atSpeedTimer.reset();
    }
    atSpeed = atSpeedTimer.done();    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSub.stop();
    atSpeed = false;
    /*
    SmartDashboard.delete("Shooter RPM");
    SmartDashboard.delete("Shooter Goal RPM");
    SmartDashboard.delete("Shooter PID - P");
    SmartDashboard.delete("Shooter PID - I");
    */
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
