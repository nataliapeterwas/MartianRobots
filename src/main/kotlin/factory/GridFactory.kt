package factory

import Grid
import Position

class GridFactory {
    fun create(width: Int, height: Int, pollutedList: MutableList<Position> = mutableListOf()) = Grid(width, height, pollutedList)
}