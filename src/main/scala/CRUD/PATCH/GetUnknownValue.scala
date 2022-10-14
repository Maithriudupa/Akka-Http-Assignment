package PATCH

import Application.USERS


object GetUnknownValue{

  def nameOfUser(name: Option[String], res: List[USERS]): String = name match {
    case Some(name) => name
    case None => res(0).name
  }

  def startTimeOfUser(startTime: Option[String], res: List[USERS]): String = startTime match {
    case Some(startTime) => startTime
    case None => res(0).startTime
  }

}
