package com.dubizzle.test.activity

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dubizzle.test.R
import com.dubizzle.test.data.network.countingIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ListingActivityTest {

    val TAG = "ListingActivityTest";

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ListingActivity::class.java, true, false)

    @Rule
    @JvmField
    public var hiltRule = HiltAndroidRule(this)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(countingIdlingResource);
        hiltRule.inject()
    }

    @Test
    fun successCase() {
        val activity = activityRule.launchActivity(null)
        Log.d(TAG, "successCase")
        val count = getRVcount(activity);
        Thread.sleep(3000)
        assert(count > 0)

    }

    @Test
    fun clickAndLaunchDetailActivity() {
        val activity = activityRule.launchActivity(null)
        Log.d(TAG, "clickAndLaunchDetailActivity")
        Intents.init();
        onView(withId(R.id.recyclerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )
        Thread.sleep(2000);
        Intents.intended(hasComponent(DetailActivity::class.java.name))
        Intents.release();

    }

    private fun getRVcount(activity: Activity): Int {
        val recyclerView =
            activity.findViewById(R.id.recyclerview) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }


    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource)
    }

}