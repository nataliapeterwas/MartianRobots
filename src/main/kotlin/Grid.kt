class Grid(
    var width: Int = 0,
    var height: Int = 0,
    val pollutedList: MutableList<Position> = mutableListOf()
) {

    fun setGrid(width: Int, height: Int) {
        require(width in 1..50 && height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        this.width = width
        this.height = height
    }

//    fun drawGrid(robot: Robot) {
//        robot.robotDirection?.let {
//            val currentPlace = it.printArrow(it)
//
//            repeat(height + 1) { yAxis ->
//                repeat(width + 1) { xAxis ->
//                    if (xAxis == robot.robotPosition.x && yAxis == height - robot.robotPosition.y) {
//                        print(currentPlace)
//                    } else {
//                        print("□ ")
//                    }
//                }
//                println()
//            }
//            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection}")
//            println() // "/n"
//
////            buildString {}
//        }
//    }

    fun toString(robot: Robot): String {
        return buildString {
            robot.robotDirection?.let {
                val currentPlace = it.printArrow(it)

                repeat(height + 1) { yAxis ->
                    repeat(width + 1) { xAxis ->
                        if (xAxis == robot.robotPosition.x && yAxis == height - robot.robotPosition.y) {
                            append(currentPlace)
                        } else {
                             append("□ ")
                        }
                    }
                    append("\n")
                }
            }
        }
    }
}