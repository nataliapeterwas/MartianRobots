package com.natalia.martianrobots

class GridRobotLogger {
    fun log(robot: Robot, grid: Grid) {
        val drawnGrid = buildString {
            robot.direction.let {
                val currentPlace = it.arrow.toString()

                repeat(grid.height + 1) { yAxis ->
                    repeat(grid.width + 1) { xAxis ->
                        if (xAxis == robot.position.x && yAxis == robot.position.y) {
                            append("$currentPlace ")
                        } else {
                            append("□ ")
                        }
                    }
                    append("\n")
                }
            }
            append("${robot.position.x} ${robot.position.y} ${robot.direction}")
            append("\n")
        }
        println(drawnGrid)
    }
}