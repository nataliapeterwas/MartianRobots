package com.natalia.mars

class Grid(
    var width: Int,
    var height: Int,
    val deadPoints: MutableList<Position> = mutableListOf()
) {
    fun isDeadPoint(x: Int, y: Int) = deadPoints.contains(Position(x, y))

    fun addDeadPoint(x: Int, y: Int) {
        deadPoints.add(Position(x, y))
    }
}