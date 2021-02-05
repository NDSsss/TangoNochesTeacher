package com.tangonoches.teacher.data.serialization

import android.util.Log
import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {

    private val formats = listOf(
        "yyyy.MM.dd"
    )

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        for (format in formats) {
            try {
                return SimpleDateFormat(format, Locale.getDefault()).parse(json.asString)
            } catch (e: ParseException) {

            }
        }
        Log.d("APP_TAG", "Unable to parse date ${json.asString}")
        throw JsonParseException("Unable to parse date ${json.asString}")
    }

}

class DateSerializer : JsonSerializer<Date> {

    override fun serialize(
        src: Date?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement =
        SimpleDateFormat("yyyy-MM-dd").format(src)?.let { JsonPrimitive(it) } ?: JsonNull.INSTANCE

}