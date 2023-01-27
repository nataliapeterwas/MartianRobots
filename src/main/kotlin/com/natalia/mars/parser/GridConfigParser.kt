package com.natalia.mars.parser

import com.natalia.mars.GridSize

class GridConfigParser {

    init {
        println("GridConfigParser")
    }
    fun parse(input: String): GridSize {
        val splitGridSize = input.split(" ").toMutableList()
        splitGridSize.removeIf { it.isEmpty() }

        require(splitGridSize.size == 2) { "Incorrect grid size" }

        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()

        require(width in 0..50 && height in 0..50) { "com.natalia.mars.Grid is rectangle: 51>x>0 and 51>y>0" }
        return GridSize(width, height)
    }
}