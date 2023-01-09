class Grid(
    var width: Int = 0,
    var height: Int = 0,
    val pollutedList: MutableList<Position> = mutableListOf()
) {

    fun setGrid(width: Int, height: Int) {
        require(width in 1..50 && height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        this.width = width
        this.height = height
    }
}