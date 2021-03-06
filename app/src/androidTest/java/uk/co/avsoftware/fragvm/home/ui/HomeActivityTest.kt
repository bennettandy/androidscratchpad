package uk.co.avsoftware.fragvm.home.ui

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.avsoftware.fragvm.ui.home.HomeActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<HomeActivity>()

    @Test
    fun quickTest() {

//        // Type text and then press the button.
//        Espresso.onView(withId(R.id.editTextUserInput))
//            .perform(ViewActions.typeText(STRING_TO_BE_TYPED), ViewActions.closeSoftKeyboard())
//        Espresso.onView(withId(R.id.changeTextBt)).perform(ViewActions.click())
//
//        // Check that the text was changed.
//        Espresso.onView(withId(R.id.textToBeChanged))
//            .check(ViewAssertions.matches(ViewMatchers.withText(STRING_TO_BE_TYPED)))
    }
}