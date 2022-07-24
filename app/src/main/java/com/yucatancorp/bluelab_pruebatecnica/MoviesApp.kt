package com.yucatancorp.bluelab_pruebatecnica

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@HiltAndroidApp
class MoviesApp: Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        checkDate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkDate() {
        val sharedPref = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val dateSaved = sharedPref.getString(getString(R.string.date_key), "")

        dateSaved?.let { mDateSaved -> saveIsDayUpdated(sharedPref, mDateSaved) }
        saveDate(sharedPref, getTodayStringDate())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveIsDayUpdated(sharedPreferences: SharedPreferences, stringDate: String) {
        val today = LocalDate.now()
        with(sharedPreferences.edit()) {
            putBoolean(
                getString(R.string.is_date_updated_key),
                stringDate.isNotEmpty() && (getDateFromString(stringDate).isBefore(today) ||
                        getDateFromString(stringDate).isEqual(today))
            )
            apply()
        }
    }

    private fun saveDate(sharedPreferences: SharedPreferences, stringDate: String) {
        with(sharedPreferences.edit()) {
            putString(
                getString(R.string.date_key),
                stringDate
            )
            apply()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTodayStringDate(): String {
        return LocalDate.now().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateFromString(dateString: String?): LocalDate {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}