package com.alifalpian.krakatauapp.domain.interactor

import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.domain.repository.FirebaseAuthRepository
import com.alifalpian.krakatauapp.domain.repository.FirebaseFirestoreRepository
import com.alifalpian.krakatauapp.domain.usecase.HomeUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeInteractor @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository
) : HomeUseCase {
    override fun isUserLogged(): Flow<FirebaseUser?> {
        return firebaseAuthRepository.isUserLogged()
    }

    override fun getUser(uid: String): Flow<Resource<User>> {
        return firebaseFirestoreRepository.getUser(uid)
    }

}