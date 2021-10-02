/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.swing.text.StyledEditorKit.BoldAction;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.classes.Switch;
import frc.robot.classes.Timer;
import frc.robot.comcon.Climber;
import frc.robot.comcon.Intake;
import frc.robot.comcon.Shooter;
import frc.robot.commands.intake.Run;
import frc.robot.commands.shooter.EmptyStorage;
import frc.robot.commands.shooter.SimpleShoot;
import frc.robot.commands.vision.AutoShooter;
import frc.robot.commands.vision.SwitchLimeLight;
import frc.robot.maps.RobotMap;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.StorageSub;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static boolean testMode = false;
  public static Switch doAuto = new Switch(false);

  public static DigitalInput autoSwitch = new DigitalInput(RobotMap.Sensors.diagnosticSwitch.Dio1());

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    LimelightSub.switchLEDs(false);
    m_robotContainer = new RobotContainer();
    doAuto.set(false);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    if (doAuto.flipOnTrue(!autoSwitch.get()))
    {
      System.out.println("Auto mode = " + doAuto.state());
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    Intake.stop();
    testMode = false;
  }

  @Override
  public void disabledPeriodic() {
    DriveSub.stop();
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    if (doAuto.state())
    {
      Shooter.toggleAutoShooter();
    }
    autoTime.reset();
  }
  
  private Timer autoTime = new Timer(2000);
  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*
    if (!autoTime.done())
    {
      DriveSub.arcadeDrive(0.75, 0);
    } else {
      DriveSub.stop();
    }
    */
  }

  @Override
  public void teleopInit() {

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    Intake.run();
    ClimbSub.teleopStart();
    ClimbSub.stow();
    Intake.deploy();

    if (OI.advancedMode())
    {
      Shooter.toggleAutoShooter();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    if (Climber.deployLiftarm.isScheduled() == false) {
      IntakeSub.intakeControl();
      StorageSub.storageControl();
      ShooterSub.shooterControl();

      SmartDashboard.putData(autoShooter);
      SmartDashboard.putData(switchLEDs);
    } else {
      SmartDashboard.delete("SwitchLimeLight");
      SmartDashboard.delete("AutoShooter");
    }

    SmartDashboard.putData(emptyStorage);
    SmartDashboard.putNumber("Limelight X", LimelightSub.getHorOffset());
    SmartDashboard.putNumber("Limelight Y", LimelightSub.getVerOffset());
    SmartDashboard.putBoolean("Do storage", Run.doStorage.state());
    
    /*
    SmartDashboard.putData(Climber.deployLiftarm);
    if (OI.deployLifer() && OI.advancedMode())
    {
      Climber.deploy();
    }
    */
  }

  private SimpleShoot soot = new SimpleShoot();
  private SwitchLimeLight switchLEDs = new SwitchLimeLight();
  private AutoShooter autoShooter = new AutoShooter();
  private EmptyStorage emptyStorage = new EmptyStorage();

  @Override
  public void testInit() {

    testMode = true;

    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    SmartDashboard.putData(autoShooter);
    SmartDashboard.putData(switchLEDs);
    SmartDashboard.putData(soot);
    SmartDashboard.putData(emptyStorage);
    SmartDashboard.putNumber("Shooter velocity", ShooterSub.getVelocity());
  }
}
