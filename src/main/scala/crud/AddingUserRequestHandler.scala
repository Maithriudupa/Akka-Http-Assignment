package crud
import application.USERS
import connection.DataBaseConnection.connection
import akka.http.scaladsl.model.StatusCodes
import java.time.OffsetDateTime
import java.time.ZoneOffset

import java.sql.PreparedStatement


class AddingUserRequestHandler{
  def addingUser(User: USERS, password: Option[String]) = {
    val pass = PasswordValidation(password)
    if (pass == "Invalid") {
      StatusCodes.custom(401, "Invalid password format")
    }
    else {
      val insertSql =
        """
          |insert into USERS (id, name, startTime, createAt, password)
          |values (?,?,?,?,?)
  """.stripMargin

      val preparedStmt: PreparedStatement = connection.prepareStatement(insertSql)
      preparedStmt.setInt(1, User.id)
      preparedStmt.setString(2, User.name)
      preparedStmt.setString(3, User.startTime)
      preparedStmt.setObject(4, OffsetDateTime.now(ZoneOffset.UTC))
      preparedStmt.setString(5, pass)
      preparedStmt.execute
      preparedStmt.close()
      StatusCodes.OK
    }

  }
}
