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
public class RobotMap
{
    public static final int mainJoy = 0;

    public enum DriveMotors
    {
          leftt1 (14)
        , leftt2 (15)
        , right1 (12)
        , right2 (13);

        int ID;
        
        DriveMotors(int ID) 
        {
            this.ID = ID;
        }
        
        public int ID()
        {
            return this.ID;
        }
    }
    
    public enum SubsystemMotors
    {
          shooter1 (1)
        , shooter2 (2)
        , winch    (9)
        , feeder   (10)
        , storage  (8)
        , intake   (6)
        , suck     (11);

        int ID;

        SubsystemMotors(int ID) 
        {
            this.ID = ID;
        }
        
        public int ID()
        {
            return this.ID;
        }
    }

    public enum Sensors
    {
          diagnosticSwitch  (0, 0) // this one is actually zero, zero
        , storageReset      (1, 0)
        , lefttDriveEncoder (2, 0)
        , climbLimitSwitch  (3, 0)
        , rightDriveEncoder (4, 0)
        , ballDetector      (8, 0)
        , storageEncoder    (9, 0);

        int DiO1;
        int DiO2;

        Sensors(int DiO1, int DiO2)
        {
            this.DiO1 = DiO1;
            this.DiO2 = DiO2;
        }
        
        public int Dio1()
        {
            return this.DiO1;
        }

        public int Dio2()
        {
            return this.DiO2;
        }
    }

    public enum PWMDevices
    {
        liftarmRelease (0);

        int PWM;

        PWMDevices(int PWM)
        {
            this.PWM = PWM;
        }

        public int PWM()
        {
            return PWM;
        }
    }
}
