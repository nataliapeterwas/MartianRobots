class Robot(
    var robotDirection: Direction,
    var robotPosition: Position = Position(0,0),
    var robotStatus: RobotStatus? = null
) {
    fun updatePosition(x: Int, y: Int){
        robotPosition = Position(x,y)
    }

    fun updateRobotStatus(robotStatus: RobotStatus){
        this.robotStatus = robotStatus
    }
}