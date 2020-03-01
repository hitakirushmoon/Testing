/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  WPI_TalonSRX left = new WPI_TalonSRX(1);
  WPI_TalonSRX leftFollow = new WPI_TalonSRX(2);
  WPI_TalonSRX right = new WPI_TalonSRX(3);
  WPI_TalonSRX rightFollow = new WPI_TalonSRX(4);
  DifferentialDrive drive = new DifferentialDrive(left, right);

  Joystick wheel;
  Joystick stick;
  public Drivebase(Joystick wheel, Joystick stick) {
    leftFollow.follow(left);
    rightFollow.follow(right);
    // right.setInverted(true);
    rightFollow.setInverted(InvertType.FollowMaster);
    this.wheel = wheel;
    this.stick = stick;
  }

  @Override
  public void periodic() {
    if (stick.getRawAxis(3) > 0) {
      drive.curvatureDrive(stick.getRawAxis(1), wheel.getRawAxis(0), wheel.getRawButton(5) || wheel.getRawButton(6));
    }
  }
}
