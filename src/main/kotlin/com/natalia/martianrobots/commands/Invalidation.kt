package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.Robot

class Invalidation {
    fun invalidatePosition(robot: Robot, grid: Grid): Boolean {
        return if (robot.position.x in 0..grid.width && robot.position.y in 0..grid.height) {
            true
        } else if (grid.isDeadPoint(robot.position.x, robot.position.y)) {
            false
        } else {
            grid.addDeadPoint(robot.position.x, robot.position.y)
            println("${robot.position.x} ${robot.position.y} ${robot.direction} LOST \n")
            robot.updateRobotStatus(false)
            false
        }
    }
}
