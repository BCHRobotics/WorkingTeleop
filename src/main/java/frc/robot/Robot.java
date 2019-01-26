/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Drivetrain m_drivetrain = null;
  public static OI m_oi = null;
  public static Grabber m_grabber = null;
  public static Lift m_lift = null;

  //Gyro
  public static final double kAngleSetpoint = 0.0;
  public static final double kP = 0.005; //Proportional turning constant
  public AnalogGyro m_gyro = new AnalogGyro(0);
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_drivetrain = new Drivetrain();
    m_oi = new OI();
    m_grabber = new Grabber();
    m_lift = new Lift();   
    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    double rotateSpeed;
    double moveSpeed;
    
    moveSpeed = (-Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVE));
    rotateSpeed = (Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE));

      /*
    if(Robot.m_oi.ButtonSnail.get() == true){

      moveSpeed = (-Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVE)) * 0.5;
      rotateSpeed = (Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE)) * 0.5;
    
    } else if(Robot.m_oi.ButtonTurbo.get() == true){

      moveSpeed = (-Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVE)) * 1;
      rotateSpeed = (Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE)) * 1;
    
    } else {

      moveSpeed = (-Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVE)) * 0.7;
      rotateSpeed = (Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE)) * 0.7;

    }
    */

    if(Robot.m_oi.ButtonSnail.get() == true){

      moveSpeed = moveSpeed * 0.5;
      rotateSpeed = rotateSpeed * 0.5;
    
    } else if(Robot.m_oi.ButtonTurbo.get() == true){

      moveSpeed = moveSpeed * 1;
      rotateSpeed = rotateSpeed * 1;
    
    } else {

      moveSpeed = moveSpeed * 0.7;
      rotateSpeed = rotateSpeed * 0.7;

    }

    Robot.m_drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
    

    if(Robot.m_oi.ButtonGrabOpen.get() == true){
        Robot.m_grabber.grabberOpen();
    } else if(Robot.m_oi.ButtonGrabClose.get() == true){
      Robot.m_grabber.grabberClose();
    } 

    double liftSpeed = Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT);

    if(Robot.m_oi.ButtonLiftEnable.get() == true){
      Robot.m_lift.MoveLift(liftSpeed);
    } else {
      Robot.m_lift.MoveLift(-0.1);
    }
    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
