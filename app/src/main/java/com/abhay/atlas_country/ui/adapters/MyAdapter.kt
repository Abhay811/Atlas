package com.abhay.atlas_country.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.abhay.atlas_country.data.models.Country
import com.abhay.atlas_country.databinding.LayoutCountryInfoBinding
import com.abhay.atlas_country.utils.DiffUtilCallback

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    val differ = AsyncListDiffer(this, DiffUtilCallback())
    private var countryList = emptyList<Country>()

    class MyViewHolder(binding: LayoutCountryInfoBinding): RecyclerView.ViewHolder(binding.root) {
        val countryName = binding.tvCountryName
        val countryCapital = binding.tvCountryCapital
        val countryLanguage = binding.tvLanguages
        val image = binding.ivCountryFlag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutCountryInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = countryList[position]
        holder.apply {
            countryName.text = currentItem.countryName
            countryCapital.text = currentItem.countryCapital
            countryLanguage.text = currentItem.languages
            image.setImageBitmap(currentItem.countryFlag)

        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setData(country: List<Country>) {
        this.countryList = country
        notifyDataSetChanged()
    }
}