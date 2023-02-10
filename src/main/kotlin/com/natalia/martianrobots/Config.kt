package com.natalia.martianrobots

import com.natalia.martianrobots.commands.Command

data class Config(
    val robotPosition: Position,
    val robotDirection: Direction,
    val gridWidth: Int,
    val gridHeight: Int,
    val commands: List<Command>,
)