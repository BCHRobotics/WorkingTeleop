package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Lift extends Subsystem{

    TalonSRX TALONLIFT = new TalonSRX(RobotMap.TALONLIFT);

    public Lift(){

        TALONLIFT.configOpenloopRamp(1);

    }

    public void MoveLift(double liftSpeed){

        TALONLIFT.set(ControlMode.PercentOutput, liftSpeed);

    }

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}