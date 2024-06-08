package com.github.jameshnsears.quoteunquote.cloud.transfer

import com.github.jameshnsears.quoteunquote.cloud.CloudTransfer
import com.github.jameshnsears.quoteunquote.cloud.CloudTransferHelper
import com.google.gson.Gson
import io.mockk.spyk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TransferTest {
    private lateinit var cloudTransfer: CloudTransfer
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        cloudTransfer = spyk()
        gson = CloudTransferHelper.getGson()
    }

    @After
    fun shutdown() {
        CloudTransfer.shutdown()
    }

    @Test
    fun backupSingleWidget() {
        val transferJson = gson.toJson(transfer())

        assertTrue(
            "",
            cloudTransfer.backup(transferJson),
        )

        // give GCP time to populate Firestore
        Thread.sleep(10000)
    }

    @Test
    fun restoreSingleWidget() {
        val transferCode = "700000008c"

        val transferRestoreRequestJson = gson.toJson(TransferRestoreRequest(transferCode))

        val transferRestoreResponse =
            cloudTransfer.restore(
                CloudTransfer.TIMEOUT_SECONDS,
                transferRestoreRequestJson,
            )

        assertTrue("", transferRestoreResponse?.transfer?.code == transferCode)
        assertTrue("", transferRestoreResponse?.error == "")
        assertTrue("", transferRestoreResponse?.reason == "")
    }

    @Test
    fun restoreUsingUnknownCode() {
        val transferCode = "80000000c4"

        val transferRestoreRequestJson = gson.toJson(TransferRestoreRequest(transferCode))

        val transferRestoreResponse =
            cloudTransfer.restore(
                CloudTransfer.TIMEOUT_SECONDS,
                transferRestoreRequestJson,
            )

        assertTrue("", transferRestoreResponse?.transfer?.code == null)
        assertTrue("", transferRestoreResponse?.transfer?.current == null)
        assertTrue("", transferRestoreResponse?.transfer?.favourites == null)
        assertTrue("", transferRestoreResponse?.transfer?.previous == null)
        assertTrue("", transferRestoreResponse?.transfer?.settings == null)

        assertEquals("", "JSON not valid", transferRestoreResponse?.error)
        assertEquals("", "no JSON for code", transferRestoreResponse?.reason)
    }

    private fun transfer(): Transfer {
        val widgetId = 1
        val code = "700000008c"
        val digest = "d0123456"
        val contentType = 12

        return Transfer(
            code,
            listOf(Current(digest, widgetId, "internal")),
            listOf(Favourite(digest, 1, "internal")),
            listOf(Previous(contentType, digest, 1, widgetId, "internal")),
            listOf(settings(widgetId)),
        )
    }

    private fun settings(widgetId: Int) = Settings(
        Quotations(
            true,
            true,
            "",
            false,
            1,
            "",
            false,
            false,
            false,
            0,
            "",
            true,
            false,
            false,
            "",
            "",
            "",
            false,
        ),
        Appearance(
            0,
            "",
            "",
            "",
            false,
            false,
            0,
            "",
            0,
            "",
            false,
            0,
            "",
            false,
            false,
            "",
            false,
            false,
            false,
            false,
            true,
            false,
            false,
            false,
        ),
        Schedule(
            false,
            true,
            false,
            true,
            false,
            false,
            false,
            0,
            0,
            false,
            0,
            23,
        ),
        Sync(
            false
        ),
        widgetId,
    )
}
