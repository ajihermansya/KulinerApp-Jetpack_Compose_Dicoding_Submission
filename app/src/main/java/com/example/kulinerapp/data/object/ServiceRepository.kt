package com.example.kulinerapp.data.`object`


import com.example.kulinerapp.data.local.Dao
import com.example.kulinerapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ServiceRepository {
    @Provides
    @ViewModelScoped
    fun provideRepository(kulinerDao: Dao) = Repository(kulinerDao)
}