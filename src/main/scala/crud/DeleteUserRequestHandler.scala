package crud


import connection.DataBaseConnection.connection
import akka.http.scaladsl.model.StatusCodes

import java.sql.PreparedStatement

class DeleteUserRequestHandler  {
  def deleteUser(UserId: Int) = {
    val statement = connection.createStatement()
    val rs = statement.executeQuery(s"SELECT * FROM USERS where id=$UserId")
    if (rs.next()) {
      val deleteSql =
        """
          |DELETE FROM USERS where id = ?
""".stripMargin
      val preparedStmt: PreparedStatement = connection.prepareStatement(deleteSql)
      preparedStmt.setInt(1, UserId)
      preparedStmt.execute
      preparedStmt.close()
      println(s"Deleted user details of user Id $UserId")
      StatusCodes.OK

    }
    else {
      StatusCodes.custom(401, s"User with user id $UserId is not present")
    }
  }


}
