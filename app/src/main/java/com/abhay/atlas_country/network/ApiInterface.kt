package com.abhay.atlas_country.network

import com.abhay.atlas_country.data.models.ResponseCountries
import com.abhay.atlas_country.data.models.ResponseCountry
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("all")
    fun getCountries(): Call<ResponseCountries>
}