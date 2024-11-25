package com.example.chef_who.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.data.local.MealsDatabase
import com.example.chef_who.core.data.manager.LocalUserManagerImpl
import com.example.chef_who.core.data.network.dto.ChefWhoApi
import com.example.chef_who.core.data.network.dto.MealsRepositoryImpl
import com.example.chef_who.core.data.network.dto.UsersRepositoryImpl
import com.example.chef_who.core.domain.manager.LocalUserManager
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.core.domain.repository.UserRepository
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.app_entry.GetCartItems
import com.example.chef_who.core.domain.usecases.app_entry.ReadAppEntry
import com.example.chef_who.core.domain.usecases.app_entry.SaveAppEntry
import com.example.chef_who.core.domain.usecases.app_entry.SaveCartItem
import com.example.chef_who.core.domain.usecases.auth.AuthUseCases
import com.example.chef_who.core.domain.usecases.auth.LoginAuth
import com.example.chef_who.core.domain.usecases.auth.RegisterAuth
import com.example.chef_who.core.domain.usecases.meals.CreateOrder
import com.example.chef_who.core.domain.usecases.meals.DeleteMeals
import com.example.chef_who.core.domain.usecases.meals.GetCartList
import com.example.chef_who.core.domain.usecases.meals.GetCategoryIds
import com.example.chef_who.core.domain.usecases.meals.GetHomeType
import com.example.chef_who.core.domain.usecases.meals.GetMenuList
import com.example.chef_who.core.domain.usecases.meals.GetSellerList
import com.example.chef_who.core.domain.usecases.meals.InsertMeals
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import com.example.chef_who.core.domain.usecases.meals.SearchMeals
import com.example.chef_who.core.domain.usecases.meals.SelectMeals
import com.example.chef_who.core.domain.usecases.meals.SelectSingleMeal
import com.example.chef_who.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger),
        saveCartItem = SaveCartItem(localUserManger),
        getCartItems = GetCartItems(localUserManger)
    )


    @Provides
    @Singleton
    fun provideAuthUseCases(
        mUserRepository: UserRepository,

        ): AuthUseCases {
        return AuthUseCases(
            loginAuth = LoginAuth(mUserRepository),
            registerAuth = RegisterAuth(mUserRepository)

        )
    }


    @Provides
    @Singleton
    fun provideMealsApi(): ChefWhoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChefWhoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMealsRepository(mealApi: ChefWhoApi): MealsRepository =
        MealsRepositoryImpl(mealApi)

    @Provides
    @Singleton
    fun provideUsersRepository(mealApi: ChefWhoApi): UserRepository =
        UsersRepositoryImpl(mealApi)

    @Provides
    @Singleton
    fun provideMealsUseCases(
        mealsRepository: MealsRepository,
        mealsDao: MealsDao
    ): MealsUseCases {
        return MealsUseCases(
            searchMeals = SearchMeals(mealsRepository),
            insertMeals = InsertMeals(mealsDao),
            deleteMeals = DeleteMeals(mealsDao),
            selectMeals = SelectMeals(mealsDao),
            selectSingleMeal = SelectSingleMeal(mealsDao),
            mHomeType = GetHomeType(mealsRepository),
            getCategoryIds = GetCategoryIds(mealsRepository),
            getMenuList = GetMenuList(mealsRepository),
            getCartIds = GetCartList(mealsRepository),
            createOrder = CreateOrder(mealsRepository),
            getSellerList = GetSellerList(mealsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideMealsDatabase(application: Application): MealsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MealsDatabase::class.java,
            name = "news_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideMealssDao(
        mealsDatabase: MealsDatabase
    ): MealsDao = mealsDatabase.mealsDao


}