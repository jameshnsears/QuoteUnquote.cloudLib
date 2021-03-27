package com.github.jameshnsears.quoteunquote.cloud

import org.junit.Assert.assertTrue
import org.junit.Test

class BuildConfigTest {
    @Test
    fun `that BuildConfig populated from gradle`() {
        assertTrue("", BuildConfig.REMOTE_DEVICE_ENDPOINT.endsWith(".cloudfunctions.net"))
    }
}
