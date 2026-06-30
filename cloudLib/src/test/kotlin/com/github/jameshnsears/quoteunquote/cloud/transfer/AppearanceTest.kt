package com.github.jameshnsears.quoteunquote.cloud.transfer

import com.github.jameshnsears.quoteunquote.cloud.CloudTransferHelper
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class AppearanceTest {
    @Test
    fun serialization() {
        val appearance =
            Appearance(
                appearanceTransparency = 10,
                appearanceColour = "Red",
                appearanceTextFamily = "Sans",
                appearanceTextStyle = "Bold",
                appearanceTextForceItalicRegular = true,
                appearanceTextCenter = false,
                appearanceTextRightSource = true,
                appearanceTextSize = 12,
                appearanceTextColour = "Blue",
                appearanceAuthorTextSize = 10,
                appearanceAuthorTextColour = "Green",
                appearanceAuthorTextHide = false,
                appearancePositionTextSize = 8,
                appearancePositionTextColour = "Black",
                appearancePositionTextHide = true,
                appearanceForceFollowSystemTheme = false,
                appearanceToolbarColour = "White",
                appearanceToolbarFirst = true,
                appearanceToolbarPrevious = false,
                appearanceToolbarFavourite = true,
                appearanceToolbarShare = false,
                appearanceToolbarShareNoSource = true,
                appearanceToolbarJump = false,
                appearanceToolbarRandom = true,
                appearanceToolbarSequential = false,
                appearanceToolbarPosition = 1,
            )

        val gson = CloudTransferHelper.getGson()
        val json = gson.toJson(appearance)

        val appearanceFromJson = gson.fromJson(json, Appearance::class.java)

        assertThat(appearanceFromJson, equalTo(appearance))

        // Verify specific field mapping to ensure @SerializedName is working as expected
        val jsonMap = gson.fromJson(json, Map::class.java)
        assertThat(jsonMap["APPEARANCE_TRANSPARENCY"], equalTo(10.0))
        assertThat(jsonMap["APPEARANCE_COLOUR"], equalTo("Red"))
        assertThat(jsonMap["APPEARANCE_TEXT_FORCE_ITALIC_REGULAR"], equalTo(true))
    }
}
