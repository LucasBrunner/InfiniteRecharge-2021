/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.classes.Timer;
import frc.robot.comcon.Intake;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.IntakeSub;

public class DeployLiftarm extends CommandBase {

  private final ClimbSub climbSub = RobotContainer.climbSub;
  private final IntakeSub intakeSub = RobotContainer.intakeSub;

  private Timer climbHesitate = new Timer(1000);

  /**
   * Creates a new DeployLiftarm.
   */
  public DeployLiftarm() {
    addRequirements(climbSub);
    addRequirements(intakeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climbHesitate.reset();
    Intake.deploy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (climbHesitate.done() == true)
    {
      ClimbSub.deploy();
      ClimbSub.runClimbMotor(OI.climbPower());
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ClimbSub.stow();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
