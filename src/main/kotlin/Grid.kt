class Grid {
//    var x = 0
//    var y = 0
//    var width = 0
//    var height = 0
//    var robotDirection = Direction.W
//    var robotPosition = Position(0, 0)
//    fun setGrid(width: Int, height: Int) {
//        require(width in 1..50 && height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
//        this.width = width
//        this.height = height
//    }
//
//    fun setRobotPosition(x: Int, y: Int, direction: Direction) {
//        this.x = x
//        this.y = y
//        robotDirection = direction
//        robotPosition = Position(x, y)
//    }
    fun drawGrid(
        width: Int,
        height: Int,
        x: Int,
        y: Int,
        direction: Direction
    ) {
        require(x in 0..width && y in 0..height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        val currentPlace = when (direction) {
            Direction.N -> "↑ "
            Direction.S -> "↓ "
            Direction.E -> "→ "
            Direction.W -> "← "
        }
        repeat(height + 1) { yAxis ->
            repeat(width + 1) { xAxis ->
                if (xAxis == x && yAxis == height - y) {
                    print(currentPlace)
                } else {
                    print("□ ")
                }
            }
            println()
        }
        println()
    }
}