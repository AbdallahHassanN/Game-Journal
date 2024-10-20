package com.example.steamdbmockup.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
object DateUtils {
    fun getDateRangeFromStartOfYear(): String {
        val currentDate = LocalDate.now()
        val startOfYear = LocalDate.of(currentDate.year, 1, 1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return "${startOfYear.format(formatter)},${currentDate.format(formatter)}"
    }

    fun getDateRangeFromStartOf2019(): String {
        val currentDate = LocalDate.now()
        val startOfYear = LocalDate.of(2019, 1, 1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        return "${startOfYear.format(formatter)},${currentDate.format(formatter)}"
    }

}