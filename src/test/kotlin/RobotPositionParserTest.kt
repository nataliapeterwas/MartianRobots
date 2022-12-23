import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RobotPositionParserTest{

    @Test
    fun `robotPositionParser throws exception when we pass incorrect robotPosition (it length is 1)`() {
        // given
        val input = "1"

        // when
        val actual = {RobotPositionParser(input).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `robotPositionParser throws exception when we pass incorrect robotPosition (incorrect format - 1 E 1)`() {
        // given
        val input = "1 E 1"

        // when
        val actual = {RobotPositionParser(input).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `RobotPositionParser correctly transforms string '30 5 E' to robotPosition and robotDirection`() {
        // given
        val robotPosition = "30 5 E"

        // when
        val actual = RobotPositionParser(robotPosition).robotPositionParser()

        // then
        actual.first shouldBeEqualTo Position(30, 5)
        actual.second shouldBeEqualTo Direction.E
    }

    @Test
    fun `RobotPositionParser throw exception when its width is 60 and its bigger than grid size`() {
        // given
        val robotPosition = "60 5 K"

        // when
        val actual = { RobotPositionParser(robotPosition).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class
    }
}