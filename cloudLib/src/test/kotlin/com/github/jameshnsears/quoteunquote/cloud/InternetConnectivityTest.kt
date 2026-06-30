package com.github.jameshnsears.quoteunquote.cloud

import io.mockk.every
import io.mockk.spyk
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class InternetConnectivityTest {
    private lateinit var cloudTransfer: CloudTransfer

    @Before
    fun setUp() {
        cloudTransfer = spyk()
    }

    @After
    fun shutdown() {
        CloudTransfer.shutdown()
    }

    @Test
    fun internetNotAvailable() {
        every { cloudTransfer.socket } throws IOException()
        assertFalse("", cloudTransfer.isInternetAvailable())
    }

    @Test
    fun internetDisappearedMidway() {
        every { cloudTransfer.socket } throws InterruptedException()
        assertFalse("", cloudTransfer.isInternetAvailable())
    }
}
