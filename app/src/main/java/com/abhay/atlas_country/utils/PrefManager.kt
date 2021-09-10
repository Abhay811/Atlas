package com.abhay.atlas_country.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    var preference : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor : SharedPreferences.Editor = preference.edit()


}