package net.svishch.android.outerspace

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.svishch.android.outerspace.ui.MainActivity
import net.svishch.android.outerspace.ui.adapter.MarsPhotosRVAdapter
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestRecyclerView {


    private lateinit var scenario: ActivityScenario<MainActivity>


    @Before
    fun setup() {
        //  scenario = launchFragmentInContainer()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(2000)

    }

    @Test
    fun scrollTo10() {
        onView(allOf(withId(R.id.rv_mars_photos), isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MarsPhotosRVAdapter.ViewHolder>(
                    10,
                    click()
                )
            )



        onView(allOf(withId(R.id.tv_photo_id_val), isDisplayed()))
            .check(matches(withText("796610")))
    }

    @Test
    fun scrollTo5AndBack() {
        onView(allOf(withId(R.id.rv_mars_photos), isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MarsPhotosRVAdapter.ViewHolder>(
                    5,
                    click()
                )
            )

        onView(allOf(withId(R.id.tv_photo_camera_val), isDisplayed())).check(
            matches(
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )

        onView(
            allOf(
                withId(R.id.action_bar_container),
                isDisplayed()
            )
        ).perform(click())
    }

    @After
    fun close() {
        scenario.close()
    }
}