import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"
val koinVersion = "3.2.2"
val mockkVersion = "1.13.2"
val kluentVersion = "1.68"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("io.insert-koin:koin-core:$koinVersion")
    testImplementation ("io.mockk:mockk:${mockkVersion}")
    testImplementation("org.amshove.kluent:kluent:${kluentVersion}")
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