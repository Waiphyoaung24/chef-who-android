package com.example.chef_who.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chef_who.core.domain.models.Category

@Database(entities = [Category::class], version = 3)
abstract class MealsDatabase : RoomDatabase() {

    abstract val mealsDao: MealsDao

}