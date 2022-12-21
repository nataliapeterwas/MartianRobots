import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameTest{
    @Test
    fun `startGame works correctly - it sets robot on correct position after changes`(){
        //given
        val grid = Grid()
        val parser = Parser()
        val robot = Robot()
        val sut = Game(grid, parser, robot)

        //when
        sut.startGame("""
             5 3
             1 1 E
             RFRFRFRF
        """.trimIndent())

        //then
        robot.robotPosition shouldBeEqualTo Position(1,1)
    }

    @Test
    fun `startGame works correctly - it sets robot in correct direction after changes`(){
        //given
        val grid = Grid()
        val parser = Parser()
        val robot = Robot()
        val sut = Game(grid, parser, robot)

        //when
        sut.startGame("""
             5 3
             1 1 E
             RFRFRFRF
        """.trimIndent())

        //then
        robot.robotDirection shouldBeEqualTo Direction.E
    }


    //??????
    @Test
    fun `startGame works correctly - it breaks app when robot has been lost`(){
        //given
        val forward = mockk<MoveForwardCommand>()
        val grid = Grid()
        val parser = Parser()
        val robot = Robot()
        val sut = Game(grid, parser, robot)

        //when
        sut.startGame("""
             5 3
             0 0 S
             FFFFFF
        """.trimIndent())

        //then
        verify (exactly = 1) { grid.drawGrid(robot)
        }
    }

    @Test
    fun `startGame works correctly - robot status is lost when he went outside grid`(){
        //given
        val forward = mockk<MoveForwardCommand>()
        val grid = Grid()
        val parser = Parser()
        val robot = Robot()
        val sut = Game(grid, parser, robot)

        //when
        sut.startGame("""
             5 3
             0 0 S
             FFFFFF
        """.trimIndent())

        //then
        robot.robotStatus shouldBeEqualTo Status.LOST
    }


}