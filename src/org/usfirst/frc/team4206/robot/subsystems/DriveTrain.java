/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
	
	
	
	public DriveTrain() {
		
	/* ----------BASIC DRIVETRAIN STUFF ----------*/	
		
	leftDriveMaster = new WPI_TalonSRX(Robot.rm.leftDriveMaster);
	leftDriveSlave = new WPI_TalonSRX(Robot.rm.leftDriveSlave);
	rightDriveMaster = new WPI_TalonSRX(Robot.rm.rightDriveMaster);
	rightDriveSlave = new WPI_TalonSRX(Robot.rm.rightDriveSlave);
	
	leftDriveSlave.follow(leftDriveMaster);
	rightDriveSlave.follow(rightDriveMaster);
	
	leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	leftDriveMaster.setSensorPhase(true);
	rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
	rightDriveMaster.setSensorPhase(true);
	
	/* ---------- MOTION PROFILING ---------- */
	
	

	
	}
	
	public void ArcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
	}
	
	public void ChezyDrive(double speed, double rotation, boolean quickturn) {
		drive.curvatureDrive(speed, rotation, quickturn);
	}

	/* ----------- MOTION PROFILING COMMANNDS -----------*/
	
	//Use this to buffer the profiles to the motor controllers

	
	

	public void initDefaultCommand() {
	}
}
