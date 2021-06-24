package net.svishch.android.outerspace

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import net.svishch.android.outerspace.ui.adapter.MarsPhotosRVAdapter
import net.svishch.android.outerspace.ui.fragments.MarsPhotosFragment
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestFragmentMars {

    private lateinit var scenario: FragmentScenario<MarsPhotosFragment>

    @Before
    fun setup() {

        scenario = launchFragmentInContainer(null, R.style.Theme_OuterSpace) {
            MarsPhotosFragment()
        }
        Thread.sleep(2000)
    }

    @Test
    fun fragmentPhotos_IsDisplayed() {
        onView(withId(R.id.rv_mars_photos)).check(matches(isDisplayed()))
    }

    @Test
    fun fragment_RecyclerView() {

        onView(allOf(withId(R.id.rv_mars_photos), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<MarsPhotosRVAdapter.ViewHolder>(
                10,
                ViewActions.click()
            )
        )


    }



}