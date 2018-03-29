package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeCube extends CommandGroup {

    public IntakeCube() {
    	addParallel(new RunRollersIntake());
    	addSequential(new MoveArm(Robot.rm.exchangeangle)); // input down position

    }
}
