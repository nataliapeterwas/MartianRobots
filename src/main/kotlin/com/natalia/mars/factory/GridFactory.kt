package com.natalia.mars.factory

import com.natalia.mars.Grid
import com.natalia.mars.Position

class GridFactory {
    fun create(width: Int, height: Int, pollutedList: MutableList<Position> = mutableListOf()) = Grid(width, height, pollutedList)
}