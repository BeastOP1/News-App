

data class AuthUIState (
    val logEmail: String = "",
    val logPassword: String = "",
    val logEmailError : Boolean = false,
    val logPasswordError : Boolean = false,
    val logButtonError : Boolean = false,

    val progressShow : Boolean = false,
    val screen : String = "",
    val signEmail: String = "",
    val signPassword: String = "",
    val signConfirmPassword: String = "",
    val signEmailError : Boolean = false,
    val signPasswordError : Boolean = false,
    val signConfirmPasswordError : Boolean = false,
    val signButtonError : Boolean = false
)