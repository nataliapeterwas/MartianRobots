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

    fun hasPositionInPollutedList(x: Int, y:Int) = pollutedList.contains(Position(x,y))

    fun addPositionToPollutedList(x: Int, y:Int){
        pollutedList.add(Position(x, y))
    }
}

// Robot.setPosition(x, y)
// Robot.setPosition(Position())

//class R {
//    fun setPosition(p : Position) {
//        this.x = p.x
//        this.y = p.y
//    }
//
//    fun setPosition(x : Int, y: Int) {
//        this.x = x
//        this.y = y
//    }
//}