package uk.co.avsoftware.fragvm.data

import android.util.Log
import uk.co.avsoftware.fragvm.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {

        Log.d(TAG, "Username $username -> password size $password")

        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
        Log.d(TAG, "Logout()")
    }

    companion object {
        const val TAG = "LoginDataSource"
    }
}