/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  WPI_VictorSPX leftShooter = new WPI_VictorSPX(7);
  WPI_VictorSPX rightShooter = new WPI_VictorSPX(8);
  
  public Shooter() {
    leftShooter.follow(rightShooter);
    leftShooter.setInverted(InvertType.OpposeMaster);
    // c.start();
    
  }

  @Override
  public void periodic() {
  }
  
  public void shoot(double speed) {
    rightShooter.set(speed);
  }

}
