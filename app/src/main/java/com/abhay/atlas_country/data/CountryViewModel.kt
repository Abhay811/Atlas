package com.abhay.atlas_country.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abhay.atlas_country.data.models.Country
import com.abhay.atlas_country.data.models.ResponseCountries
import com.abhay.atlas_country.data.models.ResponseCountry
import com.abhay.atlas_country.data.repos.CountryRepos
import com.abhay.atlas_country.data.repos.CountryRepository
import com.abhay.atlas_country.db.CountryDatabase
import com.abhay.atlas_country.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CountryViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Country>>
    val myResponse: MutableLiveData<Response<ResponseCountry>> = MutableLiveData()
    val repos: CountryRepos?= null
    private val repository: CountryRepository
    val country = MutableLiveData<Resource<ResponseCountry>> ()
    init {
        val countryDao = CountryDatabase.getDatabase(application).countryDao()
        repository = CountryRepository(countryDao)

        readAllData = repository.readAllData
    }

    fun addCountry(country: Country) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCountry(country)
        }
    }

    fun deleteAllCountry() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCountry()
        }
    }
//    fun getCountry() {
//        viewModelScope.launch {
//            val response = repos?.getCountries()
//            myResponse.value = response
//            Log.d("Main", response.toString())
////            safeCountryFetch()
//        }
//    }
//    private suspend fun safeCountryFetch() {
//        country.postValue(Resource.loading(null))
//        try {
//            val response = model.getCountries()
//            Log.d("Main", response.toString())
//        }
//        catch (t: Throwable) {
//            Log.d("Main", "alajdflassadfk")
//            when(t) {
//                is IOException -> country.postValue(Resource.error(null, "Network Failure"))
//                else -> country.postValue(Resource.error(null, t.localizedMessage))
//            }
//        }
//    }

}