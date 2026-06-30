package com.github.jameshnsears.quoteunquote.cloud.transfer

import com.github.jameshnsears.quoteunquote.cloud.CloudTransferHelper
import org.junit.Assert.assertEquals
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

        assertEquals(appearance, appearanceFromJson)

        // Verify specific field mapping to ensure @SerializedName is working as expected
        val jsonMap = gson.fromJson(json, Map::class.java)
        assertEquals(10.0, jsonMap["APPEARANCE_TRANSPARENCY"])
        assertEquals("Red", jsonMap["APPEARANCE_COLOUR"])
        assertEquals(true, jsonMap["APPEARANCE_TEXT_FORCE_ITALIC_REGULAR"])
    }
}
