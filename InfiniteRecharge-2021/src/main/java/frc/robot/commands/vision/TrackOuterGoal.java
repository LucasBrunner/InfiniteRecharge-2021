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
import frc.robot.classes.Timer;
import frc.robot.comcon.Intake;
import frc.robot.comcon.Storage;
import frc.robot.comcon.Vision;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.LimelightSub;

public class TrackOuterGoal extends CommandBase {
  
  private PIDController steeringPID = new PIDController(.0005, 0.0005, 0.005);
  private Timer endDelay = new Timer(200);
  private Timer shootDelay = new Timer(1000);

  private boolean end = true;
  public static boolean FinishedShooting = false;

  private double power = 0;

<<<<<<< HEAD
  private DriveSub driveSub = RobotContainer.driveSub;

=======
>>>>>>> remotes/origin/DEV
  /**
   * Creates a new TrackOuterGoal.
   */
  public TrackOuterGoal() {
<<<<<<< HEAD
    addRequirements(driveSub);

    steeringPID.setSetpoint(0);
    steeringPID.setTolerance(2);
=======
    addRequirements(RobotContainer.driveSub);

    steeringPID.setSetpoint(0);
    steeringPID.setTolerance(3);
>>>>>>> remotes/origin/DEV
    steeringPID.setIntegratorRange(-0.1, 0.1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    LimelightSub.switchLEDs(true);
    Vision.startShootWithVision();
    FinishedShooting = false;
    end = true;
    shootDelay.reset();
    Intake.deploy();
    if (Robot.testMode)
    {
      SmartDashboard.putNumber("Steering PID - P", 0.0006);
      SmartDashboard.putNumber("Steering PID - I", 0.00045);
    }
    steeringPID.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (FinishedShooting == true)
    {
      if (end == true)
      {
        end = false;
        endDelay.reset();
      }
    } else {
      power = steeringPID.calculate(LimelightSub.getHorOffset());
      DriveSub.arcadeDrive(OI.driveYAxis(), power);
      if (steeringPID.atSetpoint() == true && Vision.shooterAtSpeed() == true && shootDelay.done())
      {
        Storage.emptyThroughShooter();
      }
    }

    if (Robot.testMode == true)
    {
      SmartDashboard.putNumber("Limelight X", LimelightSub.getHorOffset());
      SmartDashboard.putNumber("Limelight Y", LimelightSub.getVerOffset());
      steeringPID.setP(SmartDashboard.getNumber("Steering PID - P", 0.0006));
      steeringPID.setD(SmartDashboard.getNumber("Steering PID - I", 0.00045));
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
    return endDelay.done() && FinishedShooting;
  }
}
