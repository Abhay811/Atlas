package com.abhay.atlas_country.network

import com.abhay.atlas_country.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}

//class RetrofitClient {
//    companion object {
//        private val retrofit by lazy {
////            val httpClient = OkHttpClient.Builder().addInterceptor(QueryParameterAddInterceptor()).apply {
////                addInterceptor(
////                    LoggingInterceptor.Builder()
////                        .setLevel(Level.BASIC)
////                        .log(Platform.INFO)
////                        .build()
////                )
////            }.build()
////            Log.d("Main", "alpha${httpClient}")
////            Retrofit.Builder()
////                .addConverterFactory(GsonConverterFactory.create())
////                .client(httpClient)
////                .build()
//
//           Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        val api by lazy {
//            retrofit.create(ApiInterface::class.java)
//        }
//    }
//
//}