/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.comcon.Drive;
import frc.robot.comcon.Storage;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.PixySub;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.StorageSub;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
  // The robot's subsystems and commands are defined here...
  private final Command m_autoCommand = null;
  
  public static final PixySub pixySub = new PixySub();
  public static final DriveSub driveSub = new DriveSub();
  public static final IntakeSub intakeSub = new IntakeSub();
  public static final ShooterSub shooterSub = new ShooterSub();
  public static final StorageSub storageSub = new StorageSub();
  public static final LimelightSub limelightSub = new LimelightSub();
  public static final ClimbSub climbSub = new ClimbSub();
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    configureButtonBindings();
    driveSub.setDefaultCommand(Drive.operatorDrive);
    storageSub.setDefaultCommand(Storage.reachGoal);
  }
  
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
