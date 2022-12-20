package com.example.individual.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Предоставляет ходилку в сеть. Создает и держит у себя ссылки. Инициализируется в App
 * https://startandroid.ru/ru/blog/506-retrofit.html
 */
class NetworkProvider() {

    // JSON парсить
    private val gson =
        GsonBuilder()
            .create()

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    // Для логов. Интрсептор - перехватчик. Перехватывает запросы и пишет их в логи
    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor(ApiLogger()).apply { level = HttpLoggingInterceptor.Level.BODY }

    // https://square.github.io/okhttp/
    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)

    val individualApi: IndividualApi = retrofitBuilder
        .client(okHttpClientBuilder.build())
        .build()
        .create(IndividualApi::class.java)

    companion object {
        private const val BASE_URL = "http://algor.pythonanywhere.com/api/"
        private var INSTANCE: NetworkProvider? = null
        fun init() {
            if (INSTANCE == null) {
                INSTANCE = NetworkProvider()
            }
        }

        fun get(): NetworkProvider {
            return INSTANCE!!
        }
    }
}