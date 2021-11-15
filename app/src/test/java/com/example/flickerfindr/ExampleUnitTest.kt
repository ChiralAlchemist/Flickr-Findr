package com.example.flickerfindr

import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}