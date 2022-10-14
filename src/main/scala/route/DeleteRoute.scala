package route


import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.DeleteUser


object DeleteRoute{
val deleteRoute: Route =
    delete {
      path("deleteUser" / IntNumber) { userId =>
        complete(DeleteUser(userId))
      }
    }
}
