/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.classes.Switch;
import frc.robot.classes.Timer;
import frc.robot.comcon.Climber;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.StorageSub;

public class Run extends CommandBase {
  /**
   * Creates a new RunIntake.
   */
  public Run() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
  }
  
  private static Timer ballDetectDelay = new Timer(5000);
  private static Switch ballDetectedRecently = new Switch(false);
  private static int ballsDetectedRecently = 0;
  private static Timer storageDelay = new Timer(250);
  public static Switch doStorage = new Switch(true);

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    if (IntakeSub.intakeDeployed.state() == true /** should be false, fix please */ && Climber.deployLiftarm.isScheduled() == false)
    {
      IntakeSub.runIntake(OI.intakePower());
    }

    if (StorageSub.getBallDetected() && doStorage.state())
    {
      if (storageDelay.done())
      {
        StorageSub.setGoal(StorageSub.getGoal() + 1);
        storageDelay.reset();
      }
    } else {
      storageDelay.reset();
    }

    if (ballDetectedRecently.flipOnTrue(StorageSub.getBallDetected()))
    {
      ballsDetectedRecently += 1;
      if (ballsDetectedRecently >= 5)
      {
        doStorage.set(false);
      }
    }

    if (ballDetectDelay.done())
    {
      ballDetectDelay.reset();
      ballsDetectedRecently = 0;
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    IntakeSub.stopRunning();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
