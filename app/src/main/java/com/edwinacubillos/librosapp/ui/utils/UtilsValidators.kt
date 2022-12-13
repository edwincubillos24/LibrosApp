import android.util.Patterns

fun emailValidator(email: String): Boolean{
    val pattern  = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun passwordValidator(password: String): Boolean{
    return password.length >= 6
}