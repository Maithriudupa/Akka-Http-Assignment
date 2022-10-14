package Routes

import CRUD.Delete.DeleteUser
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object DeleteRoute {
  val deleteRoute: Route =
    delete {
      path("deleteUser" / IntNumber) { userId =>
        complete(DeleteUser(userId))
      }
    }
}
