package com.jericho2code.newyorktimescustom.model.rest

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jericho2code.newyorktimescustom.toDate
import java.lang.reflect.Type
import java.util.*

class GsonString2DateSerialization: JsonDeserializer<Date?> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? = json?.asString?.toDate()
}