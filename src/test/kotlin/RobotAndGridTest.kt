import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class RobotAndGridTest{
    private val robot = Robot()
    private val grid = Grid()

    @Test
    fun `setGrid works correctly`() {
        //given

        //when
        grid.setGrid(4, 6)

        //then
        grid.width shouldBeEqualTo 4
        grid.height shouldBeEqualTo 6
    }

    @Test
    fun `setGrid throws exception when width is bigger than 50`() {
        //given

        //when
        val actual = {grid.setGrid(51, 6)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

    @Test
    fun `setRobotPosition set him to correct position`(){
        //given
        grid.setGrid(5,3)

        //when
        robot.setRobotPosition(Position(1,3), Direction.W)

        //then
        robot.robotPosition shouldBeEqualTo Position(1,3)
    }

    @Test
    fun `setRobotPosition set him with correct direction`(){
        //given
        grid.setGrid(5,3)

        //when
        robot.setRobotPosition(Position(1,3), Direction.E)

        //then
        robot.robotDirection shouldBeEqualTo Direction.E
    }
}