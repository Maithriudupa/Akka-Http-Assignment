package route

import application.USERS
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import crud.AddingUserRequestHandler


object PostRoute {
  val requestHandler = new AddingUserRequestHandler()
  val postRoute: Route =
    post {
      path("addUser") {
        parameters('id.as[Int], 'name.as[String], 'startTime.as[String], 'password.?) { (id, name, startTime, password) =>
          complete(requestHandler.addingUser(USERS(id, name, startTime), password))
        }
      }
    }
}
