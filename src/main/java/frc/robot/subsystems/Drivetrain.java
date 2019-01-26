package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem{

    TalonSRX TALONLEFT1 = new TalonSRX(RobotMap.TALONLEFT1);
    TalonSRX TALONLEFT2 = new TalonSRX(RobotMap.TALONLEFT2);
    TalonSRX TALONLEFT3 = new TalonSRX(RobotMap.TALONLEFT3);

    TalonSRX TALONRIGHT1 = new TalonSRX(RobotMap.TALONRIGHT1);
    TalonSRX TALONRIGHT2 = new TalonSRX(RobotMap.TALONRIGHT2);
    TalonSRX TALONRIGHT3 = new TalonSRX(RobotMap.TALONRIGHT3);

    

    public Drivetrain(){

        double rampRate = 0.5;

        TALONLEFT1.configOpenloopRamp(rampRate);
        TALONLEFT2.configOpenloopRamp(rampRate);
        TALONLEFT3.configOpenloopRamp(rampRate);

        TALONRIGHT1.configOpenloopRamp(rampRate);
        TALONRIGHT2.configOpenloopRamp(rampRate);
        TALONRIGHT3.configOpenloopRamp(rampRate);

    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        

		TALONLEFT1.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT1.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        TALONLEFT2.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
        TALONRIGHT2.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
        
        TALONLEFT3.set(ControlMode.PercentOutput, (rotateSpeed + moveSpeed));
		TALONRIGHT3.set(ControlMode.PercentOutput, ((rotateSpeed) - moveSpeed));
		
	}

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}