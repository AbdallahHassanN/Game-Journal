package com.example.steamdbmockup.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateRangeFromStartOfYear(): String {
        val currentDate = LocalDate.now()
        val startOfYear = LocalDate.of(currentDate.year, 1, 1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return "${startOfYear.format(formatter)},${currentDate.format(formatter)}"
    }
}