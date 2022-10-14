package route

import application.USERS
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.AddingUser

object PostRoute {
  val postRoute: Route =
    post {
      path("addUser") {
        parameters('id.as[Int], 'name.as[String], 'startTime.as[String], 'password.?) { (id, name, startTime, password) =>
          complete(AddingUser(USERS(id, name, startTime), password))
        }
      }
    }
}
