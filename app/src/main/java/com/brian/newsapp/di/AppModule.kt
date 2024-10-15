package com.brian.newsapp.di

import android.app.Application
import com.brian.newsapp.domain.manger.LocalUserManger
import com.brian.newsapp.data.manger.LocalUserMangerImpl
import com.brian.newsapp.domain.usecases.AppEntryUseCases
import com.brian.newsapp.domain.usecases.ReadAppEntry
import com.brian.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(application: Application): LocalUserManger {
        return LocalUserMangerImpl(application)

    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )


}