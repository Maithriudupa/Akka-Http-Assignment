package Validation

object PasswordValidation {
  def apply(password: Option[String]): String = password match {
    case Some(password) =>
      if (!(password.exists(_.isDigit) && password.length > 8 && password.exists(_.isLetter) && password.exists(!_.isLetterOrDigit))) {
        "Invalid"
      } else password
    case None => ""

  }
}