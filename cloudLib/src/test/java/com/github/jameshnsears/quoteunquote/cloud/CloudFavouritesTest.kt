package com.github.jameshnsears.quoteunquote.cloud

import com.google.gson.Gson
import io.mockk.every
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.io.IOException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CloudFavouritesTest {
    private lateinit var cloudFavourites: CloudFavourites
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        cloudFavourites = spyk()
        gson = Gson()
    }

    @After
    fun shutdown() {
        CloudFavourites.shutdown()
    }

    @Test
    fun `internet is available`() {
        assertTrue("", cloudFavourites.isInternetAvailable)
    }

    @Test
    fun `internet is not available`() {
        every { cloudFavourites.socket } throws IOException()
        assertFalse("", cloudFavourites.isInternetAvailable)
    }

    @Test
    fun `internet availablilty executor failure`() {
        every { cloudFavourites.socket } throws InterruptedException()
        assertFalse("", cloudFavourites.isInternetAvailable)
    }

    @Test
    fun `00 save known code`() {
        assertTrue("", cloudFavourites.save(Gson().toJson(RequestTestHelper.sendRequest())))
        Thread.sleep(10000)
    }

    @Test
    fun `01 receive unknown code`() {
        val requestReceive = ReceiveRequest()
        requestReceive.code = "dcb9pNXX9e"

        val response = cloudFavourites.receive(CloudFavourites.TIMEOUT_SECONDS, gson.toJson(requestReceive))

        assertTrue("", response?.digests == null)
        assertEquals("", "JSON not valid", response?.error)
        assertEquals("", "no digests for code", response?.reason)
    }

    @Test
    fun `02 receive known code`() {
        val response = cloudFavourites.receive(30, Gson().toJson(RequestTestHelper.receiveRequest()))

        val expected = ArrayList<String>()
        expected.add("d0")
        expected.add("d1")

        assertEquals("", expected, response?.digests)
    }

    @Test
    fun `03 receive future failure`() {
        every { cloudFavourites.receiveResponseFuture } throws InterruptedException()

        assertNull("", cloudFavourites.receive(30, Gson().toJson(RequestTestHelper.receiveRequest())))
    }
}
