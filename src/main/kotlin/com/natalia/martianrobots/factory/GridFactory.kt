package com.natalia.martianrobots.factory

import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.Position

class GridFactory {
    fun create(width: Int, height: Int, pollutedList: MutableList<Position> = mutableListOf()) = Grid(width, height, pollutedList)
}