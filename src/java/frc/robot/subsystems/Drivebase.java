/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Constants
import static frc.robot.Constants.Drive_Consts.*;

public class Drivebase extends SubsystemBase
{
  /**
   * Creates a new ExampleSubsystem.
   */
  
  //Flags
  private boolean isTeleOperated = true;

  //Single Motors
  private final WPI_TalonSRX Left = new WPI_TalonSRX(Ports.LEFT);
  private final WPI_TalonSRX LeftEncoded = new WPI_TalonSRX(Ports.LEFT_ENCODED);
  private final WPI_TalonSRX Right = new WPI_TalonSRX(Ports.RIGHT);
  private final WPI_TalonSRX RightEncoded = new WPI_TalonSRX(Ports.RIGHT_ENCODED);

  //Motor groups
  private final SpeedControllerGroup LeftMotors = new SpeedControllerGroup(Left, LeftEncoded);
  private final SpeedControllerGroup RightMotors = new SpeedControllerGroup(Right, RightEncoded);

  //Differential Drive
  private final DifferentialDrive m_Drive = new DifferentialDrive(LeftMotors, RightMotors);
  
  //Gyro
  private final AHRS Gyro = new AHRS();
  
  //Joysticks
  Joystick _Wheel;
  Joystick _Stick;

  //Constuctors
  public Drivebase(Joystick Wheel, Joystick Stick) 
  {
    // right.setInverted(true);
    _Wheel = Wheel;
    _Stick = Stick;
    LeftEncoded.setSensorPhase(true);
  }

  //Speed-limited constructor
  public Drivebase(Joystick Wheel, Joystick Stick, final double maxOutput)
  {
    _Wheel = Wheel;
    _Stick = Stick;
    m_Drive.setMaxOutput(maxOutput);
    LeftEncoded.setSensorPhase(true);
  } 

  //Teleoperated-disabled constructor
  public Drivebase()
  {
    isTeleOperated = false;
    LeftEncoded.setSensorPhase(true);
  }

  //Speed-limited, teleoperated-disabled constructor
  public Drivebase(final double maxOutput)
  {
    isTeleOperated = false;
    m_Drive.setMaxOutput(maxOutput);
    LeftEncoded.setSensorPhase(true);
  }

  //Wrapper function for the motors.
  public void tankDrive(final double leftSpeed, final double rightSpeed)
  {
    m_Drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(final double xSpeed,final double zRotation)
  {
    m_Drive.arcadeDrive(xSpeed, zRotation);
  }

  public void stop()
  {
    m_Drive.stopMotor();
  }

  public void setMaxOutput(final double maxOutput)
  {
    m_Drive.setMaxOutput(maxOutput);
  }

  public void setSelectedSensorPosition(int sensorPos)
  {
      LeftEncoded.setSelectedSensorPosition(sensorPos);
      RightEncoded.setSelectedSensorPosition(sensorPos);
  }

  public double getSensorMetricPosition(final boolean getLeft)
  {
    if (getLeft)
    return LeftEncoded.getSelectedSensorPosition() / Wheel.ENCODER_UNITS_PER_ROUND * Wheel.WHEEL_RADIUS;
    else return  RightEncoded.getSelectedSensorPosition() / Wheel.ENCODER_UNITS_PER_ROUND * Wheel.WHEEL_RADIUS;
  }

  public int getSelectedSensorPosition(final boolean getLeft)
  {
    if (getLeft)
    return LeftEncoded.getSelectedSensorPosition();
    else return  RightEncoded.getSelectedSensorPosition();
  }

  public double getSensorMetricPosition()
  {
    //Returns the average sensor value for both Wheels.
    return (getSensorMetricPosition(false) + getSensorMetricPosition(true)) / 2.0;
  }
  
  //Wrapper functions for the Gyro.
  public void resetGyro()
  {
    Gyro.reset();
  }

  public double getGyroAngle()
  {
    return Gyro.getAngle();
  }
  
  public double getTurnRate()
  {
    return Gyro.getRate();
  }

  @Override
  public void periodic()
  {
    if (isTeleOperated)
    {
      if (_Stick.getRawAxis(3) > 0)
      {
        m_Drive.curvatureDrive(_Stick.getRawAxis(1), _Wheel.getRawAxis(0), _Wheel.getRawButton(5) || _Wheel.getRawButton(6));
      }
    }
  }
}
