/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// replase me with pidCommand

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.classes.Equations;
import frc.robot.subsystems.IntakeSub;

public class Retract extends CommandBase {

  private PIDController PID = new PIDController(.075, 0, 0);

  private double moveSpeed = 0;
  
  public Retract() {
    addRequirements(RobotContainer.intakeSub);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    PID.setTolerance(5);
    PID.setSetpoint(Constants.encoderLimits.intakeUp.get());
    IntakeSub.intakeDeployed.set(false);
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    moveSpeed = IntakeSub.getPos();
    moveSpeed = PID.calculate(moveSpeed);
    moveSpeed = Equations.clamp(moveSpeed / 100, -0.5, 0.5);

    IntakeSub.moveIntake(moveSpeed);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    IntakeSub.stopMoving();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
