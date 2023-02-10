package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.GridRobotLogger
import com.natalia.martianrobots.Robot

class CommandProcessor(
    private val gridRobotLogger: GridRobotLogger,
    private val moveForwardCommand: MoveForwardCommand,
    private val moveLeftCommand: MoveLeftCommand,
    private val moveRightCommand: MoveRightCommand,
) {
    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.isAlive) {
                gridRobotLogger.log(robot, grid)
                when(command){
                    is MoveForwardCommand -> moveForwardCommand.execute(robot, grid)
                    is MoveLeftCommand -> moveLeftCommand.execute(robot)
                    is MoveRightCommand -> moveRightCommand.execute(robot)
                }
            }
        }

        if (robot.isAlive) {
            gridRobotLogger.log(robot, grid)
        }
    }
}