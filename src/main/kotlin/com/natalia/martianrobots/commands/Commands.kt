package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Robot

sealed interface Command {
    fun execute(robot: Robot)
}

class MoveRightCommand : Command {
   override fun execute(robot: Robot) {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.E
            Direction.S -> Direction.W
            Direction.E -> Direction.S
            Direction.W -> Direction.N
        }
    }
}

class MoveLeftCommand : Command {
    override fun execute(robot: Robot) {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.W
            Direction.S -> Direction.E
            Direction.E -> Direction.N
            Direction.W -> Direction.S
        }
    }
}

class MoveForwardCommand : Command {
    override fun execute(robot: Robot){
        when (robot.robotDirection) {
            Direction.N -> robot.robotPosition.y -= 1
            Direction.S -> robot.robotPosition.y += 1
            Direction.E -> robot.robotPosition.x += 1
            Direction.W -> robot.robotPosition.x -= 1
        }

//        if (temporaryX in 0..grid.width && temporaryY in 0..grid.height) {
//            robot.updatePosition(temporaryX, temporaryY)
//        } else if (!grid.isDeadPoint(temporaryX, temporaryY)) {
//            grid.addDeadPoint(temporaryX, temporaryY)
//            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection} LOST \n")
//            robot.updateRobotStatus(false)
//        }
    }
}
