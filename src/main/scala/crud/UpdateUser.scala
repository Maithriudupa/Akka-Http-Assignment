package crud

import application.{OptionUSERS, USERS}
import connection.DataBaseConnection.connection
import akka.http.scaladsl.model.StatusCodes
import crud.GetUnknownValue.{nameOfUser, startTimeOfUser}
import crud.GetUser.getAllUserBasedOnId

import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.sql.PreparedStatement


object UpdateUser {
  def apply(OptionUser: OptionUSERS, UserId: Int) = {

    val pass = PasswordValidation(OptionUser.password)
    val resultList = getAllUserBasedOnId(UserId)
    if (pass == "Invalid" || resultList.isEmpty) {
      if(pass == "Invalid")
        StatusCodes.custom(401, "Invalid password format")
      else
        StatusCodes.custom(401, "Invalid user id")
    }
    else {
      val resultList = getAllUserBasedOnId(UserId)
      val name = nameOfUser(OptionUser.name, resultList)
      val startTime = startTimeOfUser(OptionUser.startTime, resultList)

      val UpdateSql =
        """
          |UPDATE USERS
          |SET name = ? , startTime= ?, createAt = ?, password = ?
          |WHERE id = ?
  """.stripMargin

      val preparedStmt: PreparedStatement = connection.prepareStatement(UpdateSql)

      preparedStmt.setString(1, name)
      preparedStmt.setString(2, startTime)
      preparedStmt.setObject(3, OffsetDateTime.now(ZoneOffset.UTC))
      preparedStmt.setString(4, pass)
      preparedStmt.setInt(5, UserId)

      preparedStmt.execute

      preparedStmt.close()

      StatusCodes.OK
    }
  }
}
