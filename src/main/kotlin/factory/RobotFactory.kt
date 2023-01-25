package factory

import Direction
import Position
import Robot
import RobotStatus

class RobotFactory {
    fun create(
        robotDirection: Direction,
        robotPosition: Position,
        robotStatus: RobotStatus = RobotStatus.ALIVE
    ) = Robot(robotDirection, robotPosition, robotStatus)
}