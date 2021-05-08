package com.github.jameshnsears.quoteunquote.cloud

class RequestTestHelper {
    private constructor() {
        // ...
    }

    companion object {
        const val code = "4EXRqu8N68"

        fun sendRequest(): SaveRequest {
            val requestSave = SaveRequest()
            requestSave.code = code

            val digests = ArrayList<String>()
            digests.add("d0")
            digests.add("d1")
            requestSave.digests = digests

            return requestSave
        }

        fun receiveRequest(): ReceiveRequest {
            val requestReceive = ReceiveRequest()
            requestReceive.code = code
            return requestReceive
        }
    }
}
