package com.github.jameshnsears.quoteunquote.cloud

import org.junit.Assert.assertTrue
import org.junit.Test

class BuildConfigTest {
    @Test
    fun `that BuildConfig populated from gradle`() {
        // this doesn't work on F-Droid!
        assertTrue("", BuildConfig.REMOTE_DEVICE_ENDPOINT.endsWith(".cloudfunctions.net"))
    }
}
