package CRUD.Delete

import Application.USERS
import Get.GetUser.statement
import Connection.DataBaseConnection.connection
import akka.http.scaladsl.model.StatusCodes

import java.sql.PreparedStatement

object DeleteUser {
  def apply(UserId: Int) = {
    val insertSql =
      """
        |DELETE FROM USERS where id = ?
""".stripMargin
    val preparedStmt: PreparedStatement = connection.prepareStatement(insertSql)
    preparedStmt.setInt(1, UserId)
    preparedStmt.execute
    preparedStmt.close()
    println(s"Deleted user details of user Id $UserId")
    StatusCodes.OK

  }
}
