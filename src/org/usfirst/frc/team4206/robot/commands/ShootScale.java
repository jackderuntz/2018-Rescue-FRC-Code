package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootScale extends CommandGroup {

    public ShootScale() {
    	addParallel(new RunShooter(3));
        addSequential(new MoveArm(Robot.rm.scaleangle)); // up
        addSequential(new RunRollersExhale(1)); 
    }
}
