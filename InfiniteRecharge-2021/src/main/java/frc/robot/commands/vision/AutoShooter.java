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
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.classes.Equations;
import frc.robot.classes.Timer;
import frc.robot.comcon.Intake;
import frc.robot.comcon.Storage;
import frc.robot.comcon.Vision;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.LimelightSub;

public class AutoShooter extends CommandBase {
  
  private PIDController steeringPID = new PIDController(0.05, 0.01, 0);
  private Timer shootDelay = new Timer(1000);

  public static boolean FinishedShooting = false;

  private double steeringPower = 0;

  private DriveSub driveSub = RobotContainer.driveSub;

  /**
   * Creates a new TrackOuterGoal.
   */
  public AutoShooter() {
    addRequirements(driveSub);

    steeringPID.setSetpoint(0);
    steeringPID.setTolerance(2);
    
    if (Robot.testMode)
    {
      // SmartDashboard.putNumber("Steering PID - P", 0.05);
      // SmartDashboard.putNumber("Steering PID - I", 0.01);
      // SmartDashboard.putNumber("Steering PID - D", 0.00);
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LimelightSub.switchLEDs(true);
    Vision.startShootWithVision();
    FinishedShooting = false;
    Intake.deploy();
    shootDelay.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {      
    steeringPower = steeringPID.calculate(LimelightSub.getHorOffset());
    steeringPower = Equations.range(LimelightSub.getHorOffset(), -1, 1) ? 0 : steeringPower;
    DriveSub.arcadeDrive(OI.driveYAxis(), Equations.clamp(steeringPower, -0.5, 0.5) + Equations.clamp(OI.drivexAxis(), -0.5, 0.5));

    if (steeringPID.atSetpoint() == true && Vision.shooterAtSpeed() == true && shootDelay.done())
    {
      Storage.emptyThroughShooter();
      shootDelay.reset();
    }    

    if (Robot.testMode == true)
    {
      SmartDashboard.putNumber("Limelight X", LimelightSub.getHorOffset());
      SmartDashboard.putNumber("Limelight Y", LimelightSub.getVerOffset());
      // steeringPID.setP(SmartDashboard.getNumber("Steering PID - P", 0.05));
      // steeringPID.setI(SmartDashboard.getNumber("Steering PID - I", 0.01));
      // steeringPID.setD(SmartDashboard.getNumber("Steering PID - D", 0.00));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LimelightSub.switchLEDs(false);
    Vision.endShootWithVision();
    SmartDashboard.delete("Limelight X");
    SmartDashboard.delete("Limelight Y");
    SmartDashboard.delete("Steering PID - P");
    SmartDashboard.delete("Steering PID - I");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return FinishedShooting;
  }
}
