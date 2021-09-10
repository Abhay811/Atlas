package com.abhay.atlas_country.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abhay.atlas_country.data.models.Country


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCountry(country: Country)

    @Query("SELECT * FROM countries ORDER BY id ASC")
    fun readAllData(): LiveData<List<Country>>

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountry()

}