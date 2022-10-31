package edu.uoc.android

import android.content.ContentProviderOperation.newCall
import com.google.android.gms.common.api.Api
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MockServerUnitTest {
    // Create a MockWebServer. These are lean enough that you can create a new
    // instance for every unit test.
    val server = MockWebServer()

    val dispatcher: Dispatcher = object : Dispatcher() {
        @Throws(InterruptedException::class)
        override fun dispatch(request: RecordedRequest): MockResponse {
            when (request.path) {
                "/api/dataset/museus/" -> return MockResponse().setResponseCode(200)
                    .setBody(Utils.BODY)
            }
            return MockResponse().setResponseCode(404)
        }
    }

    @Before
    fun SetupMockServer(){
        server.start()
        server.dispatcher = dispatcher
    }

    @Test
    fun MuseumMockServerTesting () {
        val url = server.url( "/api/dataset/museus/")

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        val response = call.execute()

        Assert.assertEquals(Utils.BODY, response.body?.string())
    }

}
