package com.natalia.mars

class CommandProcessor(
    private val gridRobotLogger: GridRobotLogger,
) {
    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.robotStatus == RobotStatus.ALIVE) {
                gridRobotLogger.log(robot, grid)
                when(command){
                    is MoveForwardCommand -> MoveForwardCommand.execute(robot, grid)
                    is MoveLeftCommand -> MoveLeftCommand.execute(robot)
                    is MoveRightCommand -> MoveRightCommand.execute(robot)
                }
            }
        }

        if (robot.robotStatus == RobotStatus.ALIVE) {
            gridRobotLogger.log(robot, grid)
        }
    }
}