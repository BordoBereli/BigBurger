package com.kutluoglu.demo.cache.database.utils

import android.util.Log
import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by F.K. on 2019-05-02
 *
 */

class DateConverter {
    private var format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    /**
     * Utility funs
     */
    fun toDateStringFromLong(timestamp: Long?) : String {
        val dateFromDB = toDate(timestamp)
        return fromDateToString(dateFromDB)
    }

    fun fromStringDateToLong(value: String?) : Long {
        val dateFromString = toDateFromString(value)
        return toTimestamp(dateFromString)?.let { it } ?: 0
    }

    private fun toDateFromString(value: String?) : Date {
        return when(value) {
            null -> Date()
            else -> try {
                format.parse(value)
            } catch (pe: ParseException) {
                Log.e("Converters", "fromStringToDate: " + pe.message)
                Date()
            }
        }
    }

    private fun fromDateToString(date: Date?) : String {
        return when (date) {
            null -> "01.01.1970"
            else -> format.format(date)
        }
    }

    /*val byDate: Comparator<DbVakitEntity> = object : Comparator<DbVakitEntity> {
        override fun compare(ord1: DbVakitEntity, ord2: DbVakitEntity): Int {
            val d1: Date = ord1.date
            val d2: Date = ord2.date

            return when (d1.time < d2.time) { //ascending
                true -> -1
                else -> 1
            }
        }
    }*/
}