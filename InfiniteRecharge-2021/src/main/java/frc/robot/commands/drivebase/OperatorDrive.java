/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.classes.Equations;
import frc.robot.subsystems.DriveSub;

public class OperatorDrive extends CommandBase 
{
<<<<<<< HEAD
  private DriveSub driveSub = RobotContainer.driveSub;
=======
>>>>>>> remotes/origin/DEV

  /**
   * Creates a new OperatorDrive.
   */
  public OperatorDrive() {
<<<<<<< HEAD
    addRequirements(driveSub);
=======
    addRequirements(RobotContainer.driveSub);
>>>>>>> remotes/origin/DEV
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveSub.arcadeDrive(Equations.deadzone(OI.driveYAxis()), Equations.deadzone(OI.drivezAxis()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
