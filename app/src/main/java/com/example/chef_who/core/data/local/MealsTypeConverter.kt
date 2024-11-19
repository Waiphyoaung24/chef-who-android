package com.example.chef_who.core.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.chef_who.core.domain.models.Source

@ProvidedTypeConverter
class MealsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(',').let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}