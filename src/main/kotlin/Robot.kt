class Robot(
    var robotDirection: Direction,
    var robotPosition: Position,
    var robotStatus: RobotStatus = RobotStatus.ALIVE
) {
    fun updatePosition(x: Int, y: Int){
        robotPosition = Position(x,y)
    }

    fun updateRobotStatus(robotStatus: RobotStatus){
        this.robotStatus = robotStatus
    }
}