package com.cal.kotlinquizappproject.domain

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {

    private const val PREFERENCES_NAME = "quiz_preferences"
    private const val KEY_COINS = "key_coins"
    private const val KEY_NAME = "key_name"
    private const val KEY_GENDER = "key_gender"


    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun saveCoins(context: Context, coins: Int) {

        val editor = getPreferences(context).edit()
        editor.putInt(KEY_COINS, coins)
        editor.apply()
    }
    fun saveName(context: Context, name: String) {

        val editor = getPreferences(context).edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }
    fun saveGender(context: Context, gender: String) {

        val editor = getPreferences(context).edit()
        editor.putString(KEY_GENDER, gender)
        editor.apply()
    }



    fun getCoins(context: Context): Int {
        return getPreferences(context).getInt(KEY_COINS, 0)
    }
    fun getName(context: Context): String {
        return getPreferences(context).getString(KEY_NAME, "").toString()
    }
     fun getGender(context: Context): String {
        return getPreferences(context).getString(KEY_GENDER, "").toString()
    }




}