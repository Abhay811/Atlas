package com.abhay.atlas_country.ui.activities

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.abhay.atlas_country.ui.adapters.MyAdapter
import com.abhay.atlas_country.data.models.Country
import com.abhay.atlas_country.data.CountryViewModel
import com.abhay.atlas_country.data.models.ResponseCountries
import com.abhay.atlas_country.data.models.ResponseCountry
import com.abhay.atlas_country.databinding.FragmentMainBinding
import com.abhay.atlas_country.network.RetrofitClient
import com.abhay.atlas_country.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mCountryViewModel: CountryViewModel
    val country = MutableLiveData<Resource<Country>>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        mCountryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        val adapter = MyAdapter()
        binding.rvCountryCardView.adapter = adapter
        binding.rvCountryCardView.layoutManager= LinearLayoutManager(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {
            getCountry()
        }


        mCountryViewModel.readAllData.observe(viewLifecycleOwner, { country->
            adapter.setData(country)
        })


        binding.floatingActionButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mCountryViewModel.deleteAllCountry()
                Toast.makeText(
                    requireContext(),
                    "Successfully removed countries",
                    Toast.LENGTH_LONG
                ).show()
            }
            builder.setNegativeButton("No") {_, _ -> }
            builder.setTitle("Delete All Country and its data?")
            builder.setMessage("Are you sure you want to delete all data")

        }

        return binding.root
    }


//    private fun setUpObservers() {
//        mCountryViewModel.country.observe(this, {
//            it?.let {resource ->
//                when(resource.status) {
//                    Status.SUCCESS -> {
//                        binding.rvCountryCardView.visibility = View.VISIBLE
//                        binding.spinnerRegion.visibility = View.VISIBLE
//                        binding.svSearchCountry.visibility = View.VISIBLE
//
//                    }
//                    Status.ERROR -> {
//                        showFailedView (it.message)
//                    }
//                    Status.LOADING -> {
//                        binding.svSearchCountry.visibility = View.GONE
//                        binding.spinnerRegion.visibility = View.GONE
//                        binding.rvCountryCardView.visibility = view.GONE
//                    }
//
//                }
//            }
//        })
//    }

    private fun getCountry()
    {
        val country: Response<ResponseCountries> = RetrofitClient.api.getCountries()
        if (country.isSuccessful) {
            for (count in country.body()!!) {
                print (count.toString())
                    insertToDatabase(count)
                }
            }

    }

    private fun insertToDatabase(responseCountry: ResponseCountry)
    {
        lifecycleScope.launch(Dispatchers.IO) {
            val country = Country(
                0,
                responseCountry.name,
                responseCountry.capital,
                responseCountry.region,
                responseCountry.subregion,
                responseCountry.population.toString(),
                responseCountry.borders.toString(),
                responseCountry.languages[0].name,
                getBitmap(responseCountry.flag)
            )
            mCountryViewModel.addCountry(country)
        }
    }

    private suspend fun getBitmap(src: String?): Bitmap {
        val loading = ImageLoader.Builder(requireContext())
            .componentRegistry {
                add(SvgDecoder(requireContext()))
            }
            .build()
        val request = ImageRequest.Builder(requireContext())
            .data(src)
            .allowHardware(false)
            .build()
        val res = loading.execute(request).drawable
        return (res as BitmapDrawable).bitmap
    }
}
