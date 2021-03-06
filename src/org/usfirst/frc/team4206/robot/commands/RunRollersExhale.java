/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4206.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class RunRollersExhale extends Command {
	public RunRollersExhale() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.CubeIntake);
		requires(Robot.pdp);
	}
	
	public RunRollersExhale(double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.CubeIntake);
		requires(Robot.pdp);
		
		this.setTimeout(time);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.CubeIntake.ExhaleCube();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false | this.isTimedOut();
		
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.CubeIntake.StopRollers();
	}

}
