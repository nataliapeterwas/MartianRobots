package com.natalia.mars.di

import com.natalia.mars.*
import com.natalia.mars.factory.ConfigParserFactory
import com.natalia.mars.factory.GridFactory
import com.natalia.mars.factory.RobotFactory
import com.natalia.mars.parser.*
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