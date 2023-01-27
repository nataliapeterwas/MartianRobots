package com.natalia.martianrobots.di

import com.natalia.martianrobots.*
import com.natalia.martianrobots.factory.ConfigParserFactory
import com.natalia.martianrobots.factory.GridFactory
import com.natalia.martianrobots.factory.RobotFactory
import com.natalia.martianrobots.parser.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

var module = module {
    singleOf(::Game)
    singleOf(::CommandProcessor)
    singleOf(::GridRobotLogger)
    singleOf(::CommandProcessor)
    singleOf(::MoveRightCommand)
    singleOf(::MoveLeftCommand)
    singleOf(::MoveForwardCommand)

    // parser
    singleOf(::CommandsConfigParser)
    singleOf(::ConfigParser)
    singleOf(::GridConfigParser)
    singleOf(::MainInputConfigParser)
    singleOf(::PositionAndDirectionConfigParser)

    //factory
    singleOf(::ConfigParserFactory)
    singleOf(::GridFactory)
    singleOf(::RobotFactory)
}