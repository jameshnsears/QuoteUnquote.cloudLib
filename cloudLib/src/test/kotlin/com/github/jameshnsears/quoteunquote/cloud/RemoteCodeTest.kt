package com.github.jameshnsears.quoteunquote.cloud

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RemoteCodeTest {
    @Test
    fun remoteCode() {
        assertThat(
            CloudTransferHelper.isRemoteCodeValid(
                CloudTransferHelper.getLocalCode(),
            ),
            `is`(true),
        )

        val localCode = CloudTransferHelper.getLocalCode()

        assertThat("", localCode.length.toLong(), `is`(10L))

        // used in QuoteUnquote.cloudLib.functions
        assertThat("", CloudTransferHelper.isRemoteCodeValid("10000000d1"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("200000005e"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("30000000e8"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("40000000d4"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("500000008b"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("6000000061"), `is`(true))

        // used in QuoteUnquote.cloudLib
        assertThat("", CloudTransferHelper.isRemoteCodeValid("700000008c"), `is`(true))
        assertThat("", CloudTransferHelper.isRemoteCodeValid("80000000c4"), `is`(true))

        assertThat("", CloudTransferHelper.isRemoteCodeValid("0000000000"), `is`(false))
    }
}
