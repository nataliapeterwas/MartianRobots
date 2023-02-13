package com.natalia.martianrobots

data class Robot(
    var direction: Direction,
    var position: Position,
    var isAlive: Boolean = true
) {
    fun updatePosition(x: Int, y: Int){
        position = Position(x,y)
    }

    fun updateRobotStatus(robotStatus: Boolean){
        this.isAlive = robotStatus
    }
}