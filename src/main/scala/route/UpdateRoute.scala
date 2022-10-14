package route

import application.OptionUSERS
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.UpdateUser

object UpdateRoute {
  val updateRoute: Route =
    patch {
      path("updateUser") {
        parameters('id.as[Int], 'name.?, 'startTime.?, 'password.?) { (id, name, startTime, password) =>

          complete(UpdateUser(OptionUSERS(name, startTime, password), id))
        }
      }
    }
}
