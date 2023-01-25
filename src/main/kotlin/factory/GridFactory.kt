package factory

import Grid

class GridFactory {
    fun create(width: Int, height: Int) = Grid(width, height)
}