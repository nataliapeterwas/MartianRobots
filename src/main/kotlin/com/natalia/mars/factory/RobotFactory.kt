package com.natalia.mars.factory

import com.natalia.mars.Direction
import com.natalia.mars.Position
import com.natalia.mars.Robot
import com.natalia.mars.RobotStatus

class RobotFactory {
    fun create(
        robotDirection: Direction,
        robotPosition: Position,
        robotStatus: RobotStatus = RobotStatus.ALIVE
    ) = Robot(robotDirection, robotPosition, robotStatus)
}