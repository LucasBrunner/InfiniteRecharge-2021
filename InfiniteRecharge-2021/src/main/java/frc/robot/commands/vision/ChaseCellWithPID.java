/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.classes.Equations;
import frc.robot.pixy.Pixy2CCC.Block;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.PixySub;

public class ChaseCellWithPID extends CommandBase
<<<<<<< HEAD
{
  private DriveSub driveSub = RobotContainer.driveSub;
  
=======
{  
>>>>>>> remotes/origin/DEV
  private Block largestBlock;
  
  private PIDController zPID = new PIDController(0.4, 1000000, 0);
  private PIDController yPID = new PIDController(0.75, 1000000, 0);
  
  private double zMove = 0;
  private double yMove = 0;
  
  /**
   * Creates a new ChaseCellWithPID.
   */
  public ChaseCellWithPID() {
<<<<<<< HEAD
    addRequirements(driveSub);
=======
    addRequirements(RobotContainer.driveSub);
>>>>>>> remotes/origin/DEV
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    zPID.setTolerance(15);
    zPID.setSetpoint(0);
    
    yPID.setTolerance(10);
    yPID.setSetpoint(75);
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    PixySub.getBlocks();
    largestBlock = PixySub.getLargestBlock();
    if (largestBlock != null)
    {
      zMove = largestBlock.getX() - PixySub.getFrameWidth() / 2;
      zMove = zPID.calculate(zMove);
      zMove = Equations.clamp(zMove / 100, -0.5, .5);

      yMove = largestBlock.getWidth();
      yMove = yPID.calculate(yMove);
      yMove = Equations.clamp(yMove / 100, -0.3, 0.3);
      
      DriveSub.arcadeDrive(-yMove, zMove);
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return false;
  }
}
