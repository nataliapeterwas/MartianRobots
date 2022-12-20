class Robot {
    var robotDirection = Direction.W
    var robotPosition = Position(0, 0)
    var robotStatus = Status.ALIVE

    fun setRobotPosition(x: Int, y: Int, direction: Direction, grid: Grid) {
        require(robotPosition.x in 0..grid.width && robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }

        robotDirection = direction
        robotPosition = Position(x, y)
    }
}