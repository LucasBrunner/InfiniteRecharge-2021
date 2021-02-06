/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.StorageSub;

public class ShooterKeepVelocity extends CommandBase {
  
  private PIDController PID = new PIDController(1, 0, 0);
  double power = 0;

  /**
   * Creates a new ShooterKeepVelocity.
   */
  public ShooterKeepVelocity() {
    addRequirements(RobotContainer.shooterSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // SmartDashboard.putNumber("Shooter Goal Velocity", 0);
    // SmartDashboard.putNumber("Shooter PID P", 0.0005);
    // SmartDashboard.putNumber("Shooter PID I", 0.0002);
    // SmartDashboard.putNumber("Shooter PID D", 0);

    PID.setP(0.0005);
    PID.setI(0.0002);
    PID.setD(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    PID.setSetpoint(SmartDashboard.getNumber("Shooter Goal Velocity", 0));

    // PID.setP(SmartDashboard.getNumber("Shooter PID P", 0.0005));
    // PID.setI(SmartDashboard.getNumber("Shooter PID I", 0.0002));
    // PID.setD(SmartDashboard.getNumber("Shooter PID D", 0));

    
    if ( OI.startShooter() == true )
    {
      power = PID.calculate(ShooterSub.getVelocity() + (0.000154 * PID.getSetpoint()) + 0.048);
      StorageSub.setFeederMotor(-0.75);
    }
    if (OI.bButton() == true)
    {
      power = 0;
      StorageSub.setFeederMotor(0);
    }
    ShooterSub.spin(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ShooterSub.stop();
    SmartDashboard.delete("Goal Velocity");
    SmartDashboard.delete("Shooter PID P");
    SmartDashboard.delete("Shooter PID I");
    SmartDashboard.delete("Shooter PID D");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
