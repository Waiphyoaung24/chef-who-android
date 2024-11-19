package com.example.chef_who.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chef_who.core.domain.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(MealsTypeConverter::class)
abstract class MealsDatabase : RoomDatabase() {

    abstract val mealsDao: MealsDao

}