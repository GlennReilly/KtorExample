package testApp

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.host
import io.ktor.request.port
import io.ktor.request.uri
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.joda.time.DateTime

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                val uri = call.request.uri
                val host = call.request.host()
                val port = call.request.port()
                call.respondText("Hello World from Docker: ${DateTime.now().toString("dd/MMM/yyyy : hh:mm:ss:SS")} \n" +
                        ", host: $host:$port", ContentType.Text.Html)
            }

            get("/now"){
                call.respondText("New", ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}

//./gradlew build
//docker build --tag ktorexample:1.0 .
//docker run -m512M --cpus 1 -it -p 8080:8080 --rm ktorexample:1.0
//docker run --network=host -m512M --cpus 1 -it -p 8080:8080 --rm ktorexample:1.0
//docker container ls
//docker stop [containerName]
