//import org.amshove.kluent.*
//import org.junit.jupiter.api.Test
//
//internal class GameTest {
//    //    private var movement = mockk<Movement>()
//    private val sut = Game(Grid(), Parser())
//
//    @Test
//    fun `set the wrong width and height of the grid `() {
//        //given
//
//        //when
//        val actual = { sut.startGame("-1 -2", "", "") }
//
//        //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
//    }
//
//    @Test
//    fun `set the wrong width of the grid `() {
//        //given
//
//        //when
//        val actual = { sut.startGame("-1 2", "", "") }
//
//        //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
//    }
//
//    @Test
//    fun `set the wrong height of the grid `() {
//        //given
//
//        //when
//        val actual = { sut.startGame("1 -2", "", "") }
//
//        //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
//    }
//
//    @Test
//    fun `set the empty size of the grid `() {
//        //given
//
//        //when
//        val actual = { sut.startGame("0 0", "", "") }
//
//        //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
//    }
//
//    @Test
//    fun `set robot position`() {
//        //given
//        sut.startGame("3 4", "1 1 E", "")
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(1, 1)
//    }
//
//    @Test
//    fun `set robot direction`() {
//        //given
//        sut.startGame("3 4", "1 1 N", "")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.N
//    }
//
//    @Test
//    fun `robot turns right when has one movement`() {
//        //given
//        sut.startGame("3 4", "1 1 W", "R")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.N
//    }
//
//    @Test
//    fun `robot turns left when has one movement`() {
//        //given
//        sut.startGame("3 4", "1 1 W", "L")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.S
//    }
//
//    @Test
//    fun `robot go forward when has one movement`() {
//        //given
//        sut.startGame("3 4", "1 1 W", "F")
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(0, 1)
//    }
//
//    @Test
//    fun `robot go forward and his direction doesn't change when has one movement`() {
//        //given
//        sut.startGame("3 4", "1 1 W", "F")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.W
//    }
//
//    @Test
//    fun `robot is gone when he go outside grid`() {
//        //given
//        sut.startGame("3 4", "3 4 N", "F")
//        sut.startGame("3 4", "3 4 N", "F")
//
//        //when
//        val actual = sut.pollutedList
//
//        //then
//        actual shouldContain Position(3,5)
//    }
//
//    @Test
//    fun `robot can't go outside grid again from the same place as before`() {
//        //given
//        sut.apply {
//            startGame("3 4", "3 4 N", "F")
//            startGame("3 4", "3 4 N", "F")
//        }
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(3,4)
//    }
//
//    @Test
//    fun `robot makes two moves and his direction is all right`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FR")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.N
//    }
//
//    @Test
//    fun `robot makes two moves and his position is all right`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FR")
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(2,4)
//    }
//
//    @Test
//    fun `robot makes three moves and his position is all right`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FLF")
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(2,3)
//    }
//
//    @Test
//    fun `robot makes three moves and his direction is all right`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FLF")
//
//        //when
//        val actual = sut.robotDirection
//
//        //then
//        actual shouldBeEqualTo Direction.S
//    }
//
//    @Test
//    fun `robot makes three moves and he is gone`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FRF")
//
//        //when
//        val actual = sut.pollutedList
//
//        //then
//        actual shouldContain Position(2,5)
//    }
//
//    @Test
//    fun `after the robot has gone, new robot has his last position`() {
//        //given
//        sut.startGame("3 4", "3 4 W", "FRF")
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(2,4)
//    }
//
//    @Test
//    fun `robot cannot lost in the same place`() {
//        //given
//        sut.apply {
//            startGame("3 4", "3 4 W", "FRF")
//            startGame("3 4", "3 4 W", "F")
//        }
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(2,4)
//    }
//
//    @Test
//    fun `after the robot has gone, new robot may make move`() {
//        //given
//        sut.apply {
//            startGame("3 4", "3 4 W", "FRF")
//            startGame("3 4", "2 4 N", "RRF")
//        }
//
//        //when
//        val actual = sut.robotPosition
//
//        //then
//        actual shouldBeEqualTo Position(2,3)
//    }
//
//}