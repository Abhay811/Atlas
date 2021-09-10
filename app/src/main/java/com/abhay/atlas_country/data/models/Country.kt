package com.abhay.atlas_country.data.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "countries"
)
data class Country (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var countryName: String,
    var countryCapital: String,
    var region: String,
    var subregion: String,
    var population: String,
    var borders: String,
    var languages: String,
    var countryFlag: Bitmap
)