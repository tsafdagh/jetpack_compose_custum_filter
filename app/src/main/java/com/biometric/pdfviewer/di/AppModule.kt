package com.biometric.pdfviewer.di

import android.content.Context
import com.biometric.pdfviewer.persistence.dao.FacilityEntityDao
import com.biometric.pdfviewer.persistence.database.AppDatabase
import com.biometric.pdfviewer.repository.FacilityRepositoryImpl
import com.biometric.pdfviewer.repository.IFacilityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.facilityDao()

    @Singleton
    @Provides
    fun provideIfacilityRepository(localDataSource: FacilityEntityDao
    ): IFacilityRepository =
        FacilityRepositoryImpl(localDataSource)

}