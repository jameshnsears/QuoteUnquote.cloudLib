package com.github.jameshnsears.quoteunquote.cloud.transfer

import com.github.jameshnsears.quoteunquote.cloud.CloudTransfer
import com.github.jameshnsears.quoteunquote.cloud.CloudTransferHelper
import com.google.gson.Gson
import io.mockk.spyk
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
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

        assertThat(
            "",
            cloudTransfer.backup(transferJson),
            `is`(true),
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

        assertThat("", transferRestoreResponse?.transfer?.code, equalTo(transferCode))
        assertThat("", transferRestoreResponse?.error, equalTo(""))
        assertThat("", transferRestoreResponse?.reason, equalTo(""))
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

        assertThat("", transferRestoreResponse?.transfer?.code, nullValue())
        assertThat("", transferRestoreResponse?.transfer?.current, nullValue())
        assertThat("", transferRestoreResponse?.transfer?.favourites, nullValue())
        assertThat("", transferRestoreResponse?.transfer?.previous, nullValue())
        assertThat("", transferRestoreResponse?.transfer?.settings, nullValue())

        assertThat("", transferRestoreResponse?.error, equalTo("JSON not valid"))
        assertThat("", transferRestoreResponse?.reason, equalTo("no JSON for code"))
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

    fun settingsQuotations() =
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
        )

    private fun settings(widgetId: Int): Settings {
        val quotations = settingsQuotations()

        val appearance =
            Appearance(
                0,
                "",
                "",
                "",
                false,
                false,
                true,
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
                0,
            )

        val schedule =
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
                1,
                false,
                true,
            )

        return Settings(
            quotations,
            appearance,
            schedule,
            Sync(false),
            widgetId,
        )
    }
}
