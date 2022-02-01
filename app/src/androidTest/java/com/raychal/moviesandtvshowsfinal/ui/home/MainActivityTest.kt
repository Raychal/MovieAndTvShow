package com.raychal.moviesandtvshowsfinal.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.raychal.moviesandtvshowsfinal.R
import com.raychal.moviesandtvshowsfinal.utils.EspressoIdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun test1LoadMovieAndTvShow() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        Espresso.onView(withId(R.id.pager)).perform(swipeLeft())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Espresso.onView(withId(R.id.pager)).perform(swipeRight())
    }

    @Test
    fun test2ViewTest() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.favorite_destination)).perform(click())
        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withText("Movies")).perform(click())
//        Espresso.onView(withId(R.id.pager_fav)).perform(swipeLeft())
//        Espresso.onView(withId(R.id.pager_fav)).perform(swipeRight())
        Espresso.pressBack()
    }

    @Test
    fun test3InsertAndUpdateFavoriteMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.favorite_destination)).perform(click())
        Espresso.onView(withId(R.id.rv_favorite_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun test4InsertAndUpdateFavoriteTvShow() {
//        Espresso.onView(withId(R.id.pager)).perform(swipeLeft())
        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()

        InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.favorite_destination)).perform(click())
        Espresso.onView(withText("Tv Shows")).perform(click())
//        Espresso.onView(withId(R.id.pager_fav)).perform(swipeLeft())
        Espresso.onView(withId(R.id.rv_favorite_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_favorite)).perform(click())
        Espresso.pressBack()
    }

    @Test
    fun test5DetailMovie() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,
                    click()
                ))

        Espresso.onView(withId(R.id.image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.date_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.synopsis))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.vote_count))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.popularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.orisinal_language))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }

    @Test
    fun test6DetailTvShow() {
//        Espresso.onView(withId(R.id.pager)).perform(swipeLeft())
        Espresso.onView(withText("Tv Shows")).perform(click())
        Espresso.onView(withId(R.id.rv_tvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,
                    click()
                ))

        Espresso.onView(withId(R.id.image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.date_release))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.synopsis))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.vote_count))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.popularity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.orisinal_language))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }
}