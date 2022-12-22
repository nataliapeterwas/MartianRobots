class GridSizeParser(private val gridSize: String) {
    fun gridSizeParser(): Pair<Int, Int> {
        require(gridSize.split(" ").size == 2 && gridSize.toList()[1] == ' ') { "Incorrect grid size" }

        val splitGridSize = gridSize.split(" ")
        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()

        require(width in 0..50 && height in 0..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        return Pair(width, height)
    }
}