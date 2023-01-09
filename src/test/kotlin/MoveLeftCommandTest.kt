import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MoveLeftCommandTest{
    private val robot = mockk<Robot>()
    private val sut = MoveLeftCommand(robot)

    @Test
    fun `class MoveLeftCommand correctly change direction from W to S`(){
        // given
        every { robot.robotDirection} returns Direction.W
        justRun { robot.robotDirection = any() }

        // when
        sut.execute()

        // then
        verify { robot.robotDirection = Direction.S }
    }

}