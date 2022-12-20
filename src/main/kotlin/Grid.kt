class Grid {
    fun drawGrid(
        width: Int,
        height: Int,
        x: Int,
        y: Int,
        direction: Direction
    ) {
        require(x in 0..width && y in 0..height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        val currentPlace = when (direction) {
            Direction.N -> "↑ "
            Direction.S -> "↓ "
            Direction.E -> "→ "
            Direction.W -> "← "
        }
        repeat(height + 1) { yAxis ->
            repeat(width + 1) { xAxis ->
                if (xAxis == x && yAxis == height - y) {
                    print(currentPlace)
                } else {
                    print("□ ")
                }
            }
            println()
        }
        println()
    }
}