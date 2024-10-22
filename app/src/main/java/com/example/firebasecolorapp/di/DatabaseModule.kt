package com.example.firebasecolorapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room.databaseBuilder
import com.example.firebasecolorapp.data.local.MyDao
import com.example.firebasecolorapp.data.local.MyDataBase
import com.example.firebasecolorapp.domain.repository.MyRepository
import com.example.firebasecolorapp.utils.Contants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase( @ApplicationContext context: Context): MyDataBase {
        return databaseBuilder(
            context,
            MyDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
    @Singleton
    @Provides
    fun provideRepository(dao: MyDao):MyRepository{
        return MyRepository(dao)
    }

    @Singleton
    @Provides
    fun provideDao(database: MyDataBase): MyDao {
        return database.myDao
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

}