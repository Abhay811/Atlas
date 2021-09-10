package com.abhay.atlas_country.data.repos

import com.abhay.atlas_country.data.models.ResponseCountries
import com.abhay.atlas_country.data.models.ResponseCountry
import com.abhay.atlas_country.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class CountryRepos {
//    suspend fun getCountries(): Response<ResponseCountries> = RetrofitClient.api.getCountries()
    suspend fun getCountries(): Call<ResponseCountries> {
        return RetrofitClient.api.getCountries()
    }
}