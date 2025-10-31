package com.example

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.jetty.jakarta.EngineMain.main(args)
}

fun Application.module() {
  // install and configure Json ContentNegotiation feature
  install(ContentNegotiation) {
    json()
    // json(Json {
    //     prettyPrint = true
    //     isLenient = true
    // })
  }

  configureRouting()
}
