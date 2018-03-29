package org.usfirst.frc.team4206.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootExchange extends CommandGroup {

    public ShootExchange() {
    	
    	addSequential(new RunRollersExhale());
    }
}
