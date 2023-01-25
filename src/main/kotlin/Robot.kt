class Robot(
    var robotDirection: Direction,
    var robotPosition: Position,
    var robotStatus: RobotStatus? = null
) {
    fun updatePosition(x: Int, y: Int){
        robotPosition = Position(x,y)
    }

    fun updateRobotStatus(robotStatus: RobotStatus){
        this.robotStatus = robotStatus
    }
}