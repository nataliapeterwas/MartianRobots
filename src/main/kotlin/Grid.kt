class Grid {
//    var x = 0
//    var y = 0
    var width = 0
    var height = 0
    val pollutedList = mutableListOf<Position>()

    fun setGrid(width: Int, height: Int){
        require(width in 1..50 && height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        this.width = width
        this.height = height
    }

    fun drawGrid(
        robot: Robot
    ) {
        if (robot.robotPosition.x in 0..width && robot.robotPosition.y in 0..height) {

            val currentPlace = robot.robotDirection.printArrow(robot.robotDirection)

            repeat(height + 1) { yAxis ->
                repeat(width + 1) { xAxis ->
                    if (xAxis == robot.robotPosition.x && yAxis == height - robot.robotPosition.y) {
                        print(currentPlace)
                    } else {
                        print("□ ")
                    }
                }
                println()
            }
            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection}")
            println()
        }
    }
}