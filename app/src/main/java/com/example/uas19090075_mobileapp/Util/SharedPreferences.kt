package com.example.uas19090075_mobileapp.Util

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferences(val context: Context) {

    //companion object => dapat mengakses member dari suatu class tanpa melalui object
    companion object{
        private const val LOGIN = "LOGIN" //inisialisasi keystring
    }

    private val sp = PreferenceManager.getDefaultSharedPreferences(context)

    var login = sp.getBoolean(LOGIN, false) //param = string + defaultvalue(bool = false, int = 0, str = "")
        //boleh pake getboolean/getint/getstring/dll
        set(value) = sp.edit().putBoolean(LOGIN, value).apply()
}