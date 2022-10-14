package Application


import Routes.DeleteRoute.deleteRoute
import Get.GetUser.{getAllUser, getAllUserBasedOnId}
import Routes.UpdateRoute.updateRoute
import Routes.PostRoute.postRoute
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.Future
import spray.json._

import scala.io.StdIn
import scala.language.postfixOps

case class USERS(id: Int, name: String, startTime: String)
case class OptionUSERS(name: Option[String], startTime: Option[String], password: Option[String])

trait UserJsonProtocol extends DefaultJsonProtocol {
    implicit val UserFormat: RootJsonFormat[USERS] = jsonFormat3(USERS)
    implicit val OptionUSERSFormat: RootJsonFormat[OptionUSERS] = jsonFormat3(OptionUSERS)
}
object Route extends App  with UserJsonProtocol with SprayJsonSupport{
  implicit val system: ActorSystem = ActorSystem("HighLevelExample")
  implicit val materialise: ActorMaterializer = ActorMaterializer()
  import system.dispatcher


  implicit val timeout: Timeout = Timeout(10 seconds)


    val userServerRoute =
      pathPrefix("api" ) {
        get {
          //Get all users based on authentication i.e based user name is present in the table
          path("users" / Segment) { userName =>
            val users = Future {
              getAllUser(userName)
            }
            val result = users.map(user =>
              HttpEntity(ContentTypes.`application/json`, user.toJson.prettyPrint)
            )
            complete(result)
          } ~
            //Get user data based on user Id
            path("user" / IntNumber) { userId =>
              val users = Future {
                getAllUserBasedOnId(userId)
              }
              val result = users.map(user =>
                HttpEntity(ContentTypes.`application/json`,
                  user.toJson.prettyPrint)
              )
              complete(result)
            }
        } ~
          postRoute ~
          updateRoute ~
          deleteRoute

      }


  val bindingFuture = Http().bindAndHandle(userServerRoute, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done


}
