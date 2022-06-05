package com.github.jameshnsears.quoteunquote.cloud

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

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
        assertFalse("", cloudTransfer.isInternetAvailable(mockk<Context>()))
    }

    @Test
    fun internetDisappearedMidWay() {
        every { cloudTransfer.socket } throws InterruptedException()
        assertFalse("", cloudTransfer.isInternetAvailable(mockk<Context>()))
    }
}
