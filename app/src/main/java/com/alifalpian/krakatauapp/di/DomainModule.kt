package com.alifalpian.krakatauapp.di

import com.alifalpian.krakatauapp.data.repository.FirebaseAuthRepositoryImpl
import com.alifalpian.krakatauapp.data.repository.FirebaseFirestoreRepositoryImpl
import com.alifalpian.krakatauapp.domain.interactor.LoginInteractor
import com.alifalpian.krakatauapp.domain.repository.FirebaseAuthRepository
import com.alifalpian.krakatauapp.domain.repository.FirebaseFirestoreRepository
import com.alifalpian.krakatauapp.domain.usecase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseAuthRepository(
        firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl
    ): FirebaseAuthRepository

    @Binds
    @Singleton
    abstract fun provideLoginUseCase(
        loginInteractor: LoginInteractor
    ): LoginUseCase

    @Binds
    @Singleton
    abstract fun provideFirebaseFirestoreRepository(
        firebaseFirestoreRepositoryImpl: FirebaseFirestoreRepositoryImpl
    ): FirebaseFirestoreRepository

}