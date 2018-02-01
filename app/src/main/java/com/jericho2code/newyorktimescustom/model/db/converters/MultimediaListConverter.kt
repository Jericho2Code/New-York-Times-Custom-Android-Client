package com.jericho2code.newyorktimescustom.model.db.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jericho2code.newyorktimescustom.model.entities.Multimedia
import java.util.*

/**
 * The table of articles does not have a good primary key, and I want keep logic simple?
 * so I create MultimediaListConverter.
 * Otherwise I would make a separate table for Multimedia with an foreground key == article_id
 */

class MultimediaListConverter {
    val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<Multimedia> {
        if (value == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Multimedia>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Multimedia>?): String? {
        if (list == null) return null
        return gson.toJson(list)
    }
}