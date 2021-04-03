/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.classes.Timer;
import frc.robot.comcon.Storage;
import frc.robot.subsystems.ShooterSub;

public class EmptyStorage extends CommandBase {
  /**
   * Creates a new SimpleShoot.
   */
  public EmptyStorage() {
    addRequirements(RobotContainer.shooterSub);
  }

  private static Timer launchDelay = new Timer(1000);
  public static boolean finishedShooting = false;
  private static boolean once = false;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    launchDelay.reset();
    finishedShooting = false;
    once = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ShooterSub.spin(0.55);
    if (launchDelay.done() && !once)
    {
      Storage.emptyThroughShooter();
      once = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSub.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finishedShooting;
  }
}
