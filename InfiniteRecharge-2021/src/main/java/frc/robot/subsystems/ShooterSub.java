/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.comcon.Shooter;
import frc.robot.maps.RobotMap;

<<<<<<< HEAD
public class ShooterSub extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public ShooterSub() { }

  private static final CANSparkMax shooter1 = new CANSparkMax(RobotMap.SubsystemMotors.shooter1.ID(), MotorType.kBrushless);
  private static final CANSparkMax shooter2 = new CANSparkMax(RobotMap.SubsystemMotors.shooter2.ID(), MotorType.kBrushless);

  public static void spin(double power) 
  { 
    shooter1.set(+power);
    shooter2.set(-power); 
  }

  public static void stop() { 
    // 
    shooter1.set(0);
    // 
    shooter2.set(0); 
  }

=======
public class ShooterSub extends SubsystemBase
{
  private static final CANSparkMax shooter1 = new CANSparkMax(RobotMap.SubsystemMotors.shooter1.ID(), MotorType.kBrushless);
  private static final CANSparkMax shooter2 = new CANSparkMax(RobotMap.SubsystemMotors.shooter2.ID(), MotorType.kBrushless);
  
  @Override
  public void periodic() { }
  
  public static void spin(double power)
  {
    shooter1.set(+power);
    shooter2.set(-power);
  }
  
  public static void stop()
  {
    shooter1.set(0);
    shooter2.set(0);
  }
  
>>>>>>> remotes/origin/DEV
  public static double getVelocity()
  {
    return shooter1.getEncoder().getVelocity();
  }
<<<<<<< HEAD

=======
  
  /**
   * should be a command
   */
>>>>>>> remotes/origin/DEV
  public static void shooterControl()
  {
    if (OI.startShooter() == true)
    {
      if (OI.advancedMode() == true)
      {
        Shooter.toggleEmptyStorage();
<<<<<<< HEAD
      } else {
        Shooter.toggleAutoShooter();
      }
    }

=======
      } else
      {
        Shooter.toggleAutoShooter();
      }
    }
    
>>>>>>> remotes/origin/DEV
    if (OI.switchLights())
    {
      LimelightSub.switchLEDs();
    }
  }
<<<<<<< HEAD

  @Override
  public void periodic() { }
=======
>>>>>>> remotes/origin/DEV
}
