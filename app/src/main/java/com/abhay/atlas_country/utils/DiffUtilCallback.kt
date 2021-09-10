package com.abhay.atlas_country.utils

import androidx.recyclerview.widget.DiffUtil
import com.abhay.atlas_country.data.models.Country

class DiffUtilCallback: DiffUtil.ItemCallback<Country>() {

    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}