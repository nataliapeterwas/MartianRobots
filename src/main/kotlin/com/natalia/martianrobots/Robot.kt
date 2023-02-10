package com.natalia.martianrobots

class Robot(
    var robotDirection: Direction,
    var robotPosition: Position,
    var isAlive: Boolean = true
) {
    fun updatePosition(x: Int, y: Int){
        robotPosition = Position(x,y)
    }

    fun updateRobotStatus(robotStatus: Boolean){
        this.isAlive = robotStatus
    }
}