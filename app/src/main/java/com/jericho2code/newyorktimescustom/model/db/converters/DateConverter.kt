package com.jericho2code.newyorktimescustom.model.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.jericho2code.newyorktimescustom.toDate
import com.jericho2code.newyorktimescustom.toStringWithTimeZone
import java.util.*

/**
 * Created by Михаил on 31.01.2018.
 */

class DateConverter {
    val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): Date? {
        if (value == null) {
            return null
        }
        return value.toDate()
    }

    @TypeConverter
    fun fromList(value: Date?): String? {
        if (value == null) return null
        return value.toStringWithTimeZone()
    }
}