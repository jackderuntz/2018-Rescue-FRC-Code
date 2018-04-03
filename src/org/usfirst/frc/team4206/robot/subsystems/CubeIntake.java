/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import org.usfirst.frc.team4206.robot.commands.MoveArm;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CubeIntake extends Subsystem {
	
	 WPI_TalonSRX leftRollers;
	 WPI_TalonSRX rightRollers;
	 WPI_TalonSRX armMaster;
	 WPI_TalonSRX armSlave;
	
	double exchangeAngle;
	double switchAngle;
	double scaleAngle;
	
	int armPIDSlot;
	int armPIDLoop;
	int armMMTimeout;
	
	
	public CubeIntake() {
		leftRollers = new WPI_TalonSRX(7);
		rightRollers = new WPI_TalonSRX(31);
		armMaster = new WPI_TalonSRX(5);
		armSlave = new WPI_TalonSRX(Robot.rm.armSlave);
		
		rightRollers.follow(leftRollers);
		rightRollers.setInverted(true);
		armSlave.follow(armMaster);
		
		/* ---------- MOTION MAGIC ----------*/
		
		/* set PID profiles for the arm */
		armPIDSlot = 0;
		armPIDLoop = 0;
		armMMTimeout = 10;
		
		/* first choose the sensor */
		armMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, armPIDLoop, armMMTimeout);
		armMaster.setSensorPhase(true);
		armMaster.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		armMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, armMMTimeout);
		armMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, armMMTimeout);

		/* set the peak and nominal outputs */
		armMaster.configNominalOutputForward(0, armMMTimeout);
		armMaster.configNominalOutputReverse(0, armMMTimeout);
		armMaster.configPeakOutputForward(0.5, armMMTimeout);
		armMaster.configPeakOutputReverse(-0.5, armMMTimeout);

		/* set closed loop gains in slot0 - see documentation */
		armMaster.selectProfileSlot(armPIDSlot, armPIDLoop);
		armMaster.config_kF(7, 0.2, armMMTimeout);
		armMaster.config_kP(6, 0.2, armMMTimeout);
		armMaster.config_kI(0, 0, armMMTimeout);
		armMaster.config_kD(0, 0, armMMTimeout);
		
		/* set acceleration and vcruise velocity - see documentation */
		armMaster.configMotionCruiseVelocity(70, armMMTimeout);
		armMaster.configMotionAcceleration(50, armMMTimeout);
		
		/* zero the sensor */
		armMaster.setSelectedSensorPosition(0, armPIDSlot, armMMTimeout);
		
		
	}
	
	public void IntakeCube() {
		leftRollers.set(0.75);
		
	}
	
	public void ExhaleCube() {
		leftRollers.set(-1);
	}
	
	
	public void StopRollers() {
		leftRollers.set(0);
	}
	
	public void MoveArm(double position) {
		armMaster.set(ControlMode.PercentOutput, position*0.7);
	}
	
	public void GetRollerCurrent() {
		leftRollers.getOutputCurrent();
	}
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new MoveArm(0));
	}
}
