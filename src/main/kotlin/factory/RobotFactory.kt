package factory

import Direction
import Position
import Robot
import Status

class RobotFactory {
    fun create(
        robotDirection: Direction,
        robotPosition: Position,
        robotStatus: Status
    ) = Robot(robotDirection, robotPosition, robotStatus)
}