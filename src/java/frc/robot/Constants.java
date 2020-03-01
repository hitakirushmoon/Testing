/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

import static java.lang.Math.PI;

public final class Constants 
{
    public static final class Drive_Consts
    {
        public static final class Ports
        {
            public static final int LEFT = 1,
                                    LEFT_ENCODED = 2,
                                    RIGHT = 3,
                                    RIGHT_ENCODED = 4;
        }
        public static final class Wheel
        {
            public static final double  WHEEL_RADIUS = 381/25.0 * PI / 100.0,
                                        ENCODER_UNITS_PER_ROUND = 4096;
        }
        public static final class Autonomous_Drive
        {
            public static final double  angle_kP = 1/4096.0,
                                        angle_kI = 0.00013,
                                        angle_kD = 0.01;

            public static final double  distance_kP = 0.09,
                                        distance_kI = 0.05,
                                        distance_kD = 0;
            public static final double distanceErrorTolerance = 0.1;
        }
        public static final class Autonomous_Turn
        {
            public static final double turn_kP = 1.0/180,
                                       turn_kI = 0,
                                       turn_kD = 0;
            public static final double angleErrorTolerence = 0.1;
        }
    }
    public static final class Intake_Consts
    {
        public static final int INTAKE = 5;
    }
    public static final class Loader_Consts
    {
        public static final int LOADER = 6;
    }
    public static final class Shoot_Consts
    {
        public static final int SHOOTER1 = 7,
                                SHOOTER2 = 8,
                                SLIDER = 9;
    }
    public static final class Hook_Consts
    {
        public static final int HOOK = 10;
    }
}
