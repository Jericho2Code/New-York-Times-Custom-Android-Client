package com.jericho2code.newyorktimescustom.model.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.jericho2code.newyorktimescustom.toDate
import com.jericho2code.newyorktimescustom.toStringWithTimeZone
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

/**
 * Created by Михаил on 31.01.2018.
 */

class LocalDateTimeConverter {
    val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): ZonedDateTime? {
        if (value == null) {
            return null
        }
        return value.toDate()
    }

    @TypeConverter
    fun fromList(value: ZonedDateTime?): String? {
        if (value == null) return null
        return value.toStringWithTimeZone()
    }
}