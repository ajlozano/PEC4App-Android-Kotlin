package edu.uoc.android.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val LEVEL_LOG = HttpLoggingInterceptor.Level.BODY

    // Create an instance of Retrofit. Set the base url
    val museumAPI: MuseumService
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(MuseumService::class.java)
        }

    // Show HTTPS logs in dev mode
    private val client: OkHttpClient
        get() {
            val builderClientHttp = OkHttpClient().newBuilder()
            // Show HTTPS logs in dev mode
            if (ApiConstants.isDebugging) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = LEVEL_LOG
                builderClientHttp.addInterceptor(interceptor)
            }
            return builderClientHttp.build()
        }
}