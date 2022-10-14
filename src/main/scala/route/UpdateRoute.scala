package route

import application.OptionUSERS
import PATCH.UpdateUser
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

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
