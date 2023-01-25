import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class RobotTest{
    private val sut = Robot(Direction.N)
    private val grid = mockk<Grid>()
    @Test
    fun `setRobotPosition set him to correct position`(){
        //given
        every { grid.width } returns 5
        every { grid.height } returns 3

        //when
        sut.setRobotPosition(Position(1,3), Direction.W)

        //then
        sut.robotPosition shouldBeEqualTo Position(1,3)
    }

    @Test
    fun `setRobotPosition set him with correct direction`(){
        //given
        every { grid.width } returns 5
        every { grid.height } returns 3

        //when
        sut.setRobotPosition(Position(1,3), Direction.E)

        //then
        sut.robotDirection shouldBeEqualTo Direction.E
    }
}