package com.abhay.atlas_country.data.repos

import androidx.lifecycle.LiveData
import com.abhay.atlas_country.data.models.Country
import com.abhay.atlas_country.db.CountryDao

class CountryRepository(private val countryDao: CountryDao) {

    val readAllData: LiveData<List<Country>> = countryDao.readAllData()

    suspend fun addCountry(country: Country) {
        countryDao.addCountry(country)
    }

    suspend fun deleteAllCountry() {
        countryDao.deleteAllCountry()
    }

}