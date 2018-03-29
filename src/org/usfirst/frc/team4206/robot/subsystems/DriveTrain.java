/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.commands.PlayerDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {

	//Declaring Talons + DiffDrive
	static WPI_TalonSRX leftDriveMaster;
	static WPI_TalonSRX leftDriveSlave;
	static WPI_TalonSRX rightDriveMaster;
	static WPI_TalonSRX rightDriveSlave;
	static DifferentialDrive drive;
	
	double speed;
	double rotation;
	
	int armPIDSlot;
	int armPIDLoop;
	int armMMTimeout;
	
	
	
	public DriveTrain() {
		
	/* ----------BASIC DRIVETRAIN STUFF ----------*/	
		
	leftDriveMaster = new WPI_TalonSRX(1);
	leftDriveSlave = new WPI_TalonSRX(2);
	rightDriveMaster = new WPI_TalonSRX(3);
	rightDriveSlave = new WPI_TalonSRX(4);
	
	leftDriveSlave.follow(leftDriveMaster);
	rightDriveSlave.follow(rightDriveMaster);
	
	armPIDSlot = 0;
	armPIDLoop = 0;
	armMMTimeout = 10;
	
	drive = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	
	
	
	leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	leftDriveMaster.setSensorPhase(true);
	rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	rightDriveMaster.setSensorPhase(true);
	
	
	/* ---------- MOTION PROFILING ---------- */
	

	leftDriveMaster.setInverted(false);
	rightDriveMaster.setInverted(false);


	/* Set relevant frame periods to be at least as fast as periodic rate */
	leftDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, armMMTimeout);
	leftDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, armMMTimeout);

	/* set the peak and nominal outputs */
	leftDriveMaster.configNominalOutputForward(0, armMMTimeout);
	leftDriveMaster.configNominalOutputReverse(0, armMMTimeout);
	leftDriveMaster.configPeakOutputForward(0.75, armMMTimeout);
	leftDriveMaster.configPeakOutputReverse(-0.75, armMMTimeout);

	/* set closed loop gains in slot0 - see documentation */
	leftDriveMaster.selectProfileSlot(armPIDSlot, armPIDLoop);
	leftDriveMaster.config_kF((int) .07, 0.2, armMMTimeout);
	leftDriveMaster.config_kP(2, 0.2, armMMTimeout);
	leftDriveMaster.config_kI(0, 0, armMMTimeout);
	leftDriveMaster.config_kD(0, 0, armMMTimeout);
	
	/* set acceleration and vcruise velocity - see documentation */
	leftDriveMaster.configMotionCruiseVelocity(70, armMMTimeout);
	leftDriveMaster.configMotionAcceleration(50, armMMTimeout);
	
	leftDriveMaster.setSensorPhase(true);
	leftDriveMaster.setInverted(false);

	/* Set relevant frame periods to be at least as fast as periodic rate */
	rightDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, armMMTimeout);
	rightDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, armMMTimeout);

	/* set the peak and nominal outputs */
	rightDriveMaster.configNominalOutputForward(0, armMMTimeout);
	rightDriveMaster.configNominalOutputReverse(0, armMMTimeout);
	rightDriveMaster.configPeakOutputForward(0.5, armMMTimeout);
	rightDriveMaster.configPeakOutputReverse(-0.5, armMMTimeout);

	/* set closed loop gains in slot0 - see documentation */
	rightDriveMaster.selectProfileSlot(armPIDSlot, armPIDLoop);
	rightDriveMaster.config_kF((int) .07, 0.2, armMMTimeout);
	rightDriveMaster.config_kP(2, 0.2, armMMTimeout);
	rightDriveMaster.config_kI(0, 0, armMMTimeout);
	rightDriveMaster.config_kD(0, 0, armMMTimeout);
	
	/* set acceleration and vcruise velocity - see documentation */
	rightDriveMaster.configMotionCruiseVelocity(70, armMMTimeout);
	rightDriveMaster.configMotionAcceleration(50, armMMTimeout);
	

	
	}
	
	public void ArcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(-speed, rotation);
		
		drive.setSafetyEnabled(false);
	}
	
	public void ChezyDrive(double speed, double rotation, boolean quickturn) {
		drive.curvatureDrive(-speed, rotation, quickturn);
	}
	
	public void ZeroEncoders() {
		leftDriveMaster.setSelectedSensorPosition(0, 0, 0);
		rightDriveMaster.setSelectedSensorPosition(0, 0, 0);
	}
	
	public double LookEncoders() {
		return
		leftDriveMaster.getSelectedSensorPosition(0);
	}
	
	public void MotionMagic(double left, double right) {
		leftDriveMaster.set(ControlMode.MotionMagic, left);
		rightDriveMaster.set(ControlMode.MotionMagic, right);
	}

	/* ----------- MOTION PROFILING COMMANNDS -----------*/
	
	//Use this to buffer the profiles to the motor controllers
	


	
	

	public void initDefaultCommand() {
		setDefaultCommand(new PlayerDrive());
	}
}
