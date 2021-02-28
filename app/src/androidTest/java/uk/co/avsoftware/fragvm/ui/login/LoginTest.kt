package uk.co.avsoftware.fragvm.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.avsoftware.fragvm.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginTest {

    private lateinit var stringToBetyped: String
    private lateinit var passwordToBeTyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
        passwordToBeTyped = "Password123"
    }

//    @Test
//    fun login_test_short_password() {
//        onView(withId(R.id.username))
//            .perform(typeText(stringToBetyped))
//
//        onView(withId(R.id.password))
//            .perform(typeText("1234"))
//
//        closeSoftKeyboard()
//
//        onView(withId(R.id.login)).perform(click())
//
//    }

    @Test
    fun login_test() {
        // Type text and then press the button.
        onView(withId(R.id.username))
            .perform(typeText(stringToBetyped))

        onView(withId(R.id.password))
            .perform(typeText(passwordToBeTyped))

        onView(withId(R.id.login)).perform(click())

//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//            .check(matches(withText(stringToBetyped)))
    }
}