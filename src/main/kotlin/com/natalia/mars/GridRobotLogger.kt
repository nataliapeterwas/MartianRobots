package com.natalia.mars

class GridRobotLogger {
    fun log(robot: Robot, grid: Grid) {
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