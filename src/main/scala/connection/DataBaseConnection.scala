package connection




import java.sql.{Connection, DriverManager}
object DataBaseConnection {
  // connect to the database named "mysql" on the localhost
  val driver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://localhost/Trial"
  val username = "root"
  val password = "Kram@158!"

  // there's probably a better way to do this
  var connection: Connection = null

  try {
    // make the connection
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    println("DB connected Successfully")

    // create the statement, and run the select query
  } catch {
    case e => e.printStackTrace
  }
}
