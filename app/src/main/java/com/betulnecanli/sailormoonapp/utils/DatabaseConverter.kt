package com.betulnecanli.sailormoonapp.utils


import androidx.room.TypeConverter
import java.lang.StringBuilder

class DatabaseConverter {
    private val  seperator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String {
//        val stringBuilder = StringBuilder()
//        for (item in list) {
//            stringBuilder.append(item).append(separator)
//        }
//
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
        return list.joinToString(separator = seperator)
    }

    @TypeConverter
    fun convertStringToList(string: String) : List<String>{
        return string.split(seperator)
    }
}