/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CubeShooter extends Subsystem {
	
	static WPI_TalonSRX leftShooter;
	static WPI_TalonSRX rightShooter;

	public CubeShooter() {
		leftShooter = new WPI_TalonSRX(9); // 8
		rightShooter = new WPI_TalonSRX(10); // 9
		
		//rightShooter.follow(leftShooter);
		rightShooter.setInverted(true);
	}
	
	public void FireCube() {
		leftShooter.set(1);
		rightShooter.set(1);
	}
	
	public void StopWheels() {
		leftShooter.set(0);
		rightShooter.set(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
