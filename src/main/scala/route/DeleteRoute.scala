package route


import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.DeleteUserRequestHandler



object DeleteRoute{
  val requestHandler = new DeleteUserRequestHandler
val deleteRoute: Route =
    delete {
      path("deleteUser" / IntNumber) { userId =>
        complete(requestHandler.deleteUser(userId))
      }
    }
}
