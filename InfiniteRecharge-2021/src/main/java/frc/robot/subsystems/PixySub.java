/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.pixy.Pixy2;
import frc.robot.pixy.Pixy2.LinkType;
import frc.robot.pixy.Pixy2CCC.Block;

public class PixySub extends SubsystemBase {

  private static Pixy2 pixy;

  private static int state;
  private static int frameWidth;
  private static int frameHeight;

  private static ArrayList<Block> blocks;
  
  private static Block largestBlock;

<<<<<<< HEAD
  /**
   * Creates a new Pixy2.
   */
  public PixySub(LinkType spi) 
  {
    pixy = Pixy2.createInstance(spi);
=======
  static
  {
    pixy = Pixy2.createInstance(LinkType.SPI);
>>>>>>> remotes/origin/DEV
    state = pixy.init();
    frameWidth = pixy.getFrameWidth();
    frameHeight = pixy.getFrameHeight();
  }

<<<<<<< HEAD
  public static Block getLargestBlock() { return largestBlock; }
=======
  @Override
  public void periodic() { }

  /**
   * @return The largest object visible in the most recent call of getBlocks().
   */
  public static Block getLargestBlock() { return largestBlock; }

>>>>>>> remotes/origin/DEV
  public static int getFrameWidth() { return frameWidth; }
  public static int getFrameHeight() { return frameHeight; }
  
  public static boolean checkState()
  {
    if (state < 0)
    {
      state = pixy.init();
    }
    return state >= 0;
  }
  
<<<<<<< HEAD
  public static void getBlocks()
=======
  /**
   * Reads all of the objects visible by the camera.
   * @return The largest object visible in this read.
   */
  public static Block getBlocks()
>>>>>>> remotes/origin/DEV
  {
    blocks = null;
    largestBlock = null;
    pixy.getCCC().getBlocks(false, 255, 255);
    blocks = pixy.getCCC().getBlocks();
    if (blocks.size() > 0)
    {
      largestBlock = blocks.get(0);
      for (int i = 1; i <= blocks.size() - 1; ++i)
      {
        if (blocks.get(i).getWidth() > largestBlock.getWidth())
        {
          largestBlock = blocks.get(i);
        }
      }
    }
<<<<<<< HEAD
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
=======
    return largestBlock;
>>>>>>> remotes/origin/DEV
  }
}
