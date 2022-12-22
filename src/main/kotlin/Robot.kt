class Robot(
    var robotDirection: Direction? = null,
    var robotPosition: Position = Position(0,0),
    var robotStatus: Status? = null
) {
    fun setRobotPosition(position: Position, direction: Direction) {
        robotDirection = direction
        robotPosition = position
        robotStatus = Status.ALIVE
    }
}