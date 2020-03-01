/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivebase;
import static frc.robot.Constants.Drive_Consts.Autonomous_Drive.*;


public class DriveNMeters extends CommandBase {
  /**
   * Creates a new DriveNMeters.
   */
  double l;
  double s;
  Drivebase m_Drivebase;
  boolean isNegative = false;
  boolean set = false;

  double factor = 1;

  double I = 0;

  PIDController angle_Controller = new PIDController(angle_kP, angle_kI, angle_kD);
  PIDController distance_Controller = new PIDController(distance_kP, distance_kI, distance_kD);

  public DriveNMeters(Drivebase drivebase, final double length, final double speed) {
    l = length;
    s = Math.abs(speed);
    isNegative = (length < 0);
    if (isNegative) s = -s;
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drivebase = drivebase;
    set = false;
    factor = 1;
    addRequirements(m_Drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_Drivebase.setSelectedSensorPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    double error = m_Drivebase.getSelectedSensorPosition(true) - m_Drivebase.getSelectedSensorPosition(false);
    double PI = angle_Controller.output(error);
  //  double current_speed = s * distance_Controller.output(l - m_Drivebase.getSensorMetricPosition());
    double current_speed = 0.4;
    SmartDashboard.putNumber("Current speed: ", s);
    SmartDashboard.putNumber("Current error: ", error);
    SmartDashboard.putNumber("PI", PI);
    m_Drivebase.tankDrive(current_speed + PI, current_speed - PI);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivebase.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return Math.abs(l - m_Drivebase.getSensorMetricPosition()) < distanceErrorTolerance;
  }
}
