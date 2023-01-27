class CommandProcessor(
    private val gridRobotLogger: GridRobotLogger,
) {
    fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.robotStatus == RobotStatus.ALIVE) {
                gridRobotLogger.log(robot, grid)
                when(command){
                    is MoveForwardCommand -> command.execute(robot, grid)
                    is MoveLeftCommand -> command.execute(robot)
                    is MoveRightCommand -> command.execute(robot)
                }
            }
        }

        if (robot.robotStatus == RobotStatus.ALIVE) {
            gridRobotLogger.log(robot, grid)
        }
    }
}