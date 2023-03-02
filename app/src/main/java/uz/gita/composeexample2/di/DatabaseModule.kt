package uz.gita.composeexample2.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.composeexample2.data.source.room.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "appDB").build()

    @[Provides Singleton]
    fun provideGroupDao(appDatabase: AppDatabase) = appDatabase.groupDao()

    @[Provides Singleton]
    fun providesStudentDao(appDatabase: AppDatabase) = appDatabase.studentDao()
}