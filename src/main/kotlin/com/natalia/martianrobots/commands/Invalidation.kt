package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.Robot

class Invalidation {
    fun invalidatePosition(robot: Robot, grid: Grid): Boolean {
        return if (robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) {
            true
        } else if (grid.isDeadPoint(robot.robotPosition.x, robot.robotPosition.y)) {
            false
        } else {
            grid.addDeadPoint(robot.robotPosition.x, robot.robotPosition.y)
            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection} LOST \n")
            robot.updateRobotStatus(false)
            false
        }
    }
}