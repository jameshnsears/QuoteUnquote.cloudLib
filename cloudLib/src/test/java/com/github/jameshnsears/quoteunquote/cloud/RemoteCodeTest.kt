package com.github.jameshnsears.quoteunquote.cloud

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RemoteCodeTest {
    @Test
    fun remoteCode() {
        assertTrue(
            CloudTransferHelper.isRemoteCodeValid(
                CloudTransferHelper.getLocalCode()
            )
        )

        val localCode = CloudTransferHelper.getLocalCode()

        assertEquals("", 10, localCode.length.toLong())

        // used in QuoteUnquote.cloudLib.functions
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("10000000d1"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("200000005e"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("30000000e8"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("40000000d4"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("500000008b"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("6000000061"))

        // used in QuoteUnquote.cloudLib
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("700000008c"))
        assertTrue("", CloudTransferHelper.isRemoteCodeValid("80000000c4"))

        assertFalse("", CloudTransferHelper.isRemoteCodeValid("0000000000"))
    }
}
