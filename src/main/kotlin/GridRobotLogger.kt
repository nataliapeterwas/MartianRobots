class GridRobotLogger(
    private val robot: Robot,
    private val grid: Grid
) {
//    override fun toString(): String {
//        return buildString {
//            robot.robotDirection.let {
//                val currentPlace = it.printArrow(it)
//
//                repeat(grid.height + 1) { yAxis ->
//                    repeat(grid.width + 1) { xAxis ->
//                        if (xAxis == robot.robotPosition.x && yAxis == grid.height - robot.robotPosition.y) {
//                            append(currentPlace)
//                        } else {
//                            append("□ ")
//                        }
//                    }
//                    append("\n")
//                }
//            }
//            append("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection}")
//            append("\n")
//        }
//    }

    fun log() {
        val drawnGrid = buildString {
            robot.robotDirection.let {
                val currentPlace = it.arrow

                repeat(grid.height + 1) { yAxis ->
                    repeat(grid.width + 1) { xAxis ->
                        if (xAxis == robot.robotPosition.x && yAxis == grid.height - robot.robotPosition.y) {
                            append(currentPlace)
                        } else {
                            append("□ ")
                        }
                    }
                    append("\n")
                }
            }
            append("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection}")
            append("\n")
        }
        println(drawnGrid)
    }
}