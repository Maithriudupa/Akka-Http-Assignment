package POST
import Application.USERS
import Connection.DataBaseConnection.connection
import Validation.PasswordValidation
import akka.http.scaladsl.model.StatusCodes

import java.sql.PreparedStatement


object AddingUser{
  def apply(User: USERS, password: Option[String]) = {
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
      preparedStmt.setLong(4, System.currentTimeMillis())
      preparedStmt.setString(5, pass)
      preparedStmt.execute
      preparedStmt.close()
      StatusCodes.OK
    }

  }
}
