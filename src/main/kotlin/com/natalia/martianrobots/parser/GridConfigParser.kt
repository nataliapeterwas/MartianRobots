package com.natalia.martianrobots.parser

import com.natalia.martianrobots.GridSize

class GridConfigParser {
    fun parse(input: String): GridSize {
        val splitGridSize = input.split(" ").toMutableList()
        splitGridSize.removeIf { it.isEmpty() }

        require(splitGridSize.size == 2) { "Incorrect grid size" }

        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()

        return GridSize(width, height)
    }
}