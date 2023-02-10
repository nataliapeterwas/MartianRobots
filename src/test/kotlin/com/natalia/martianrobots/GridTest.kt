package com.natalia.martianrobots

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GridTest{
    val sut = Grid(4,6)

    @Test
    fun `addPositionToPollutedList added Position(3,4) to pollutedList`() {
        //given
        sut.addDeadPoint(3,4)

        //when
        val actual = sut.deadPoints.contains(Position(3,4))
        //then
        actual shouldBeEqualTo true
    }

    @Test
    fun `hasPositionInPollutedList returns true when Position(2,3) is in this list`() {
        //given
        sut.addDeadPoint(2,3)

        //when
        val actual = sut.isDeadPoint(2,3)

        //then
        actual shouldBeEqualTo true
    }

    @Test
    fun `hasPositionInPollutedList returns false when Position(2,3) is not in this list`() {
        //given
        sut.addDeadPoint(3,4)

        //when
        val actual = sut.isDeadPoint(2,3)

        //then
        actual shouldBeEqualTo false
    }
}