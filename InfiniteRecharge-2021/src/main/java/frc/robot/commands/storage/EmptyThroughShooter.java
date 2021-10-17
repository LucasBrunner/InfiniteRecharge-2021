/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.storage;

<<<<<<< HEAD
import edu.wpi.first.wpilibj2.command.CommandBase;
=======
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.classes.Timer;
>>>>>>> remotes/origin/DEV
import frc.robot.comcon.Intake;
import frc.robot.commands.intake.Run;
import frc.robot.commands.shooter.EmptyStorage;
import frc.robot.commands.vision.AutoShooter;
import frc.robot.commands.vision.TrackOuterGoal;
import frc.robot.subsystems.StorageSub;

public class EmptyThroughShooter extends CommandBase {

  /**
   * Creates a new EmptyThroughShooter.
   */
  public EmptyThroughShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Intake.stop();
<<<<<<< HEAD
    StorageSub.setGoal(StorageSub.getGoal() + 6);
=======
    ReachGoal.powerFun = 0.5;
    StorageSub.setGoal(StorageSub.getGoal() + 6);
    forceEnd.reset();
>>>>>>> remotes/origin/DEV
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    StorageSub.setFeederMotor(-0.75);
  }

<<<<<<< HEAD
=======
  Timer forceEnd = new Timer(5000);
  public static boolean resumeStorage = false;

>>>>>>> remotes/origin/DEV
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Intake.run();
    StorageSub.setFeederMotor(0);
    AutoShooter.FinishedShooting = true;
    TrackOuterGoal.FinishedShooting = true;
    EmptyStorage.finishedShooting = true;
<<<<<<< HEAD
    Run.doStorage.set(true);
=======
    Run.doStorage.set(resumeStorage);
    ReachGoal.powerFun = 1;
>>>>>>> remotes/origin/DEV
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean output = false;

    if ((int) StorageSub.getGoal() == (int) StorageSub.getEncoderPosition()) { output = true; }

<<<<<<< HEAD
    return output;
=======
    return output || forceEnd.done();
>>>>>>> remotes/origin/DEV
  }
}
