package com.abhay.atlas_country.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhay.atlas_country.data.CountryViewModel
import com.abhay.atlas_country.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mCountryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}