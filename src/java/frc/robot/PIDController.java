/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class PIDController 
{
    double kP, kI, kD;
    double I = 0;
    // double previous_error = Double.NaN;
    public PIDController(double kP, double kI, double kD)
    {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
    public double output(double error)
    {
        double P = kP * error;
        I += kI * 0.02 * error;
        // double D = 0;
        // if(previous_error != Double.NaN){
            // D = (error - previous_error) / 0.02;
        // }
        // previous_error = error;
        SmartDashboard.putNumber("P", P);
        SmartDashboard.putNumber("I", I);
        // SmartDashboard.putNumber("D", D);
        
        return P + I;
    }
}
