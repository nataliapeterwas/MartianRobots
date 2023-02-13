package com.natalia.martianrobots.commands

import com.natalia.martianrobots.*

class CommandProcessor(
    private val gridRobotLogger: GridRobotLogger,
    private val invalidation: Invalidation
) {
    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.isAlive) {
                gridRobotLogger.log(robot, grid)
                val beforeRobotPosition = robot.robotPosition.copy()
                command.execute(robot)
                if (command is MoveForwardCommand) {
                    if (!invalidation.invalidatePosition(robot, grid)) {
                        robot.robotPosition = beforeRobotPosition
                    }
                }
            }
        }

        if (robot.isAlive) {
            gridRobotLogger.log(robot, grid)
        }
    }
}