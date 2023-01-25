class Robot(
    var robotDirection: Direction,
    var robotPosition: Position = Position(0,0),
    var robotStatus: Status? = null
) {
    fun setRobotPosition(position: Position, direction: Direction) {
        robotDirection = direction
        robotPosition = position
        robotStatus = Status.ALIVE
    }
    fun updatePosition(x: Int, y: Int){
        robotPosition = Position(x,y)
    }

    fun updateRobotStatus(status: Status){
        robotStatus = status
    }
}