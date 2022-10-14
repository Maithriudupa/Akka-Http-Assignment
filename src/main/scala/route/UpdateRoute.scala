package route

import application.OptionUSERS
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.UpdateUserRequestHandler



object UpdateRoute {
  val requestHandler = new UpdateUserRequestHandler()
  val updateRoute: Route =
    patch {
      path("updateUser") {
        parameters('id.as[Int], 'name.?, 'startTime.?, 'password.?) { (id, name, startTime, password) =>

          complete(requestHandler updateUser(OptionUSERS(name, startTime, password), id))
        }
      }
    }
}
