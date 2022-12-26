package com.example.individual.data.net

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class NetworkProvider(context: Context) {

    private val gson =
        GsonBuilder()
            .create()

    val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ) = object : Converter<ResponseBody, Any?> {
            val nextResponseBodyConverter =
                retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

            override fun convert(value: ResponseBody): Any? =
                if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else "{}"
        }
    }

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create(gson))

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor(ApiLogger()).apply { level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)

    val api: Api = retrofitBuilder
        .client(okHttpClientBuilder.build())
        .build()
        .create(Api::class.java)

    val individualApiMock = IndividualApiMock()

    companion object {
        private val BASE_URL = "http://algor.pythonanywhere.com/api/"
        private var INSTANCE: NetworkProvider? = null
        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NetworkProvider(context)
            }
        }

        fun get(): NetworkProvider {
            return INSTANCE!!
        }
    }
}