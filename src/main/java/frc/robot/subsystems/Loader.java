/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Loader extends SubsystemBase {
 
  WPI_VictorSPX loader = new WPI_VictorSPX(6);
  WPI_TalonSRX slider = new WPI_TalonSRX(9);
  
  public Loader() {
    
  }

  @Override
  public void periodic() {
    if (RobotContainer.stick.getRawButton(12))
    {
      slide(1);
    }
    else if (RobotContainer.stick.getRawButton(11)) {
      slide(-1);
    }
    else {
      slide(0);
    }
  }
  
  public void transport(double speed) {
    loader.set(speed);
  }


  public void slide(double speed) {
    slider.set(speed);
  }

}
