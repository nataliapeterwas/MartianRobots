import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"
val koinVersion = "3.3.0"
val mockkVersion = "1.13.2"
val kluentVersion = "1.72"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
//    testImplementation ("io.insert-koin:koin-test:$koinVersion")
    implementation ("io.insert-koin:koin-core:$koinVersion")
    testImplementation ("io.mockk:mockk:${mockkVersion}")
    testImplementation("org.amshove.kluent:kluent:${kluentVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}