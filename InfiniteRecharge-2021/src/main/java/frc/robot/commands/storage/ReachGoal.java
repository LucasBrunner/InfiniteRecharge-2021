/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.storage;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.StorageSub;

public class ReachGoal extends CommandBase {

  private int goal;
  private PIDController PID = new PIDController(4, 10, .2);

  /**
   * Creates a new ReachGoal.
   */
  public ReachGoal() {
    addRequirements(RobotContainer.storageSub);
    PID.setTolerance(0.1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (goal != StorageSub.getGoal())
    {
      goal = StorageSub.getGoal();
      PID.setSetpoint(goal);
      PID.reset();
    }
    double power = PID.calculate(StorageSub.getEncoderPosition());

    StorageSub.storagePower(power * powerFun);
  }

  static double powerFun = 1;

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
