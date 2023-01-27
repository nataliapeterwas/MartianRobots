package com.natalia.martianrobots

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class RobotTest{
    private val sut = Robot(Direction.N, Position(1,3))
    @Test
    fun `updatePosition set robot to correct position`(){
        //given

        //when
        sut.updatePosition(2,4)

        //then
        sut.robotPosition shouldBeEqualTo Position(2,4)
    }

    @Test
    fun `updateRobotStatus set robot correct status`(){
        //given

        //when
        sut.updateRobotStatus(RobotStatus.LOST)

        //then
        sut.robotStatus shouldBeEqualTo RobotStatus.LOST
    }
}