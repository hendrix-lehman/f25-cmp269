// Kotlin DSL build script for a Ktor server application using Jetty with Jakarta support
// Sets Java source compatibility to version 25 and target compatibility to version 24
java {
    sourceCompatibility = JavaVersion.VERSION_25 
    targetCompatibility = JavaVersion.VERSION_24
}

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)

    kotlin("plugin.serialization") version "1.9.23"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.jetty.jakarta.EngineMain"
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.jetty.jakarta)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization)

    implementation("com.google.zxing:core:3.5.2")
}
