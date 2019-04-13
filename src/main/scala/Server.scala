import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.io.StdIn

object ServerConfiguration {
  val host = "localhost"
  val port = 9090
}

object Server extends App {
  override def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    val serverActions = new ServerActions()
    val bindingFuture = serverActions.startServer(ServerConfiguration.host, ServerConfiguration.port)

    println(s"Server online at ${ServerConfiguration.host}:${ServerConfiguration.port}\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    serverActions.stopServer(bindingFuture)
  }
}
