/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.maps;

/**
 * Add your docs here.
 */
public class ControllerMap 
{
    public enum Drivebase
    {
          yAxis (1)
        , zAxis (4);

        private int ID;

        Drivebase(int ID)
        {
            this.ID = ID;
        }

        public int ID()
        {
            return ID;
        }
    }

    public enum Intake
    {
          throttle1 (2)
        , throttle2 (3)
        , updown    (6);

        private int ID;

        Intake(int ID)
        {
            this.ID = ID;
        }

        public int ID()
        {
            return ID;
        }
    }

    public enum Climber
    {
          throttleUp  (2)
        , trottleDown (3)
        , deploy      (8);

        private int ID;

        Climber(int ID)
        {
            this.ID = ID;
        }

        public int ID()
        {
            return ID;
        }
    }

    public enum Storage
    {
          cancel  (2)
        , advMode (5);

        private int ID;
        
        Storage(int ID)
        {
            this.ID = ID;
        }

        public int ID()
        {
            return ID;
        }
    }

    public enum Shooter
    {
          Lights (1)
        , Start  (7);

        private int ID;
        
        Shooter(int ID)
        {
            this.ID = ID;
        }

        public int ID()
        {
            return ID;
        }
    }
}
