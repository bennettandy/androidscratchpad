package uk.co.avsoftware.fragvm.home.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.StringStartsWith.startsWith
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.avsoftware.fragvm.R
import uk.co.avsoftware.fragvm.ui.home.HomeActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<HomeActivity>()

    @Test
    fun quickTest() {

        // check heading
        onView(withId(R.id.text_home))
            .check(matches(withText("Latest Block on the Blockchain")))

        // hash is empty
        onView(withId(R.id.text_hash))
            .check(matches(withText("")))

        // progress is hidden
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))

        onView(withId(R.id.btn_refresh)).perform(click())

        onView(withId(R.id.text_hash))
            .check(matches(withText(startsWith("0000000000"))))


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