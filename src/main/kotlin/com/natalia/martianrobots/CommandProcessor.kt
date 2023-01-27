package com.natalia.martianrobots

class CommandProcessor(
    private val gridRobotLogger: GridRobotLogger,
    private val moveForwardCommand: MoveForwardCommand,
    private val moveLeftCommand: MoveLeftCommand,
    private val moveRightCommand: MoveRightCommand,
) {
    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.robotStatus == RobotStatus.ALIVE) {
                gridRobotLogger.log(robot, grid)
                when(command){
                    is MoveForwardCommand -> moveForwardCommand.execute(robot, grid)
                    is MoveLeftCommand -> moveLeftCommand.execute(robot)
                    is MoveRightCommand -> moveRightCommand.execute(robot)
                }
            }
        }

        if (robot.robotStatus == RobotStatus.ALIVE) {
            gridRobotLogger.log(robot, grid)
        }
    }
}