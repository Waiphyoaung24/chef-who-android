package com.example.chef_who.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chef_who.core.domain.models.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM Category")
    fun getMeals(): Flow<List<Category>>

    @Query("SELECT * FROM Category WHERE name=:url")
    suspend fun getArticle(url: String): Category?

}