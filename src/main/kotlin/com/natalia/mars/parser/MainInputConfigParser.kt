package com.natalia.mars.parser

import com.natalia.mars.InputParsedToThreeLines

class MainInputConfigParser {
    fun parse(input: String): InputParsedToThreeLines {
        require(input.isNotEmpty()) { "You must pass grid size, robot position and robot moves" }

        val splitMainInput = input.split("\n").toMutableList()

        require(splitMainInput.size == 3) { "Incorrect input" }

        val gridSize = splitMainInput[0]
        val robotPosition = splitMainInput[1]
        val moves = splitMainInput[2]

        return InputParsedToThreeLines(gridSize, robotPosition, moves)
    }
}