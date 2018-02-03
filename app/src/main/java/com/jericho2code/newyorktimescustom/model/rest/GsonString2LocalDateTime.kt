package com.jericho2code.newyorktimescustom.model.rest

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jericho2code.newyorktimescustom.toDate
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import java.lang.reflect.Type

/**
 * Created by Михаил on 25.12.2017.
 */
class GsonString2LocalDateTime : JsonDeserializer<ZonedDateTime?> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ZonedDateTime? = json?.asString?.toDate()
}