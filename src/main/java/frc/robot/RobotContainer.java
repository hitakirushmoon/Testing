/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ToggleCompressor;
import frc.robot.commands.Extend;
import frc.robot.commands.Succ;
import frc.robot.commands.Retract;
import frc.robot.commands.Shoot;
import frc.robot.commands.Transport;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Joystick wheel = new Joystick(0);
  public static final Joystick stick = new Joystick(1);

  private final Drivebase drivebase = new Drivebase(wheel, stick);
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber(stick);
  private final Loader loader = new Loader();
  private final Intake intake = new Intake();


  private final Command toggleCompressor = new ToggleCompressor(intake);
  private final Command m_autoCommand = null;


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton[] b = new JoystickButton[stick.getButtonCount()];
    for (int i = 0; i < b.length; i++) {
      b[i] = new JoystickButton(stick, i +1);
    }
    b[0].whileActiveOnce(new ParallelCommandGroup(new Shoot(shooter),
        new SequentialCommandGroup(new WaitCommand(1), new Transport(loader))));
    b[1].whileActiveOnce(new ParallelCommandGroup(new Succ(intake), new Transport(loader)));
    b[2].whenPressed(new Extend(intake).raceWith(new WaitCommand(2)));
    b[3].whenPressed(new Retract(intake).raceWith(new WaitCommand(2)));
    b[8].toggleWhenActive(toggleCompressor);

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
