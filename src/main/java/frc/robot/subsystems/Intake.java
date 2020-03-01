/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  WPI_VictorSPX intake = new WPI_VictorSPX(5);
  DoubleSolenoid intakePiston = new DoubleSolenoid(0,1);
  public Compressor c = new Compressor();
  
  public Intake() {
    
  }
  @Override
  public void periodic() {
   }
  
  
  public void intake(double speed) {
    intake.set(-speed);
  }
  
  public void extend() {
    intakePiston.set(Value.kForward);
  }

  public void retract() {
    intakePiston.set(Value.kReverse);
  }

  public void stay() {
    intakePiston.set(Value.kOff);
  }
}
