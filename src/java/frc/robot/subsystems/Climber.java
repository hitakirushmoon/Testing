/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */

  // DoubleSolenoid intakePiston = new DoubleSolenoid(0,1);
  WPI_TalonSRX hooker = new WPI_TalonSRX(10);
  Joystick stick;
  public Climber(Joystick stick) {
    this.stick = stick;
  }

  @Override
  public void periodic() {
    if (stick.getRawButton(7)) {
      hooker.set(1);
    }
    else if (stick.getRawButton(8)) {
      hooker.set(-1);
    }
    else {
    hooker.stopMotor();
    }
    // This method will be called once per scheduler run
  }
}
