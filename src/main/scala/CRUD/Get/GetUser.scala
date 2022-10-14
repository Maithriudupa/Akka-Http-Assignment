package Get

import Application.USERS
import Connection.DataBaseConnection._

import java.sql.ResultSet



object GetUser{
  val statement = connection.createStatement()
  def getAllUser(UserName: String) = {
    val resultSet = "SELECT * FROM USERS WHERE EXISTS (SELECT * FROM USERS WHERE name = ?)"
    val cs = connection.prepareCall(resultSet)
    cs.setString(1, s"$UserName")
    cs.execute
    val rs: ResultSet = cs.getResultSet
    Iterator.from(0).takeWhile(_ => rs.next()).map(_ => USERS(rs.getInt(1), rs.getString(2), rs.getString(3))).toList
  }

  def getAllUserBasedOnId(UserId: Int) = {
    val rs = statement.executeQuery(s"SELECT * FROM USERS where id=$UserId")
    Iterator.from(0).takeWhile(_ => rs.next()).map(_ => USERS(rs.getInt(1), rs.getString(2), rs.getString(3))).toList
    }



}
