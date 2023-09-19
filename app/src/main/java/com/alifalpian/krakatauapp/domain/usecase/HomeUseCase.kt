package com.alifalpian.krakatauapp.domain.usecase

import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {

    fun isUserLogged(): Flow<FirebaseUser?>

    fun getUser(uid: String): Flow<Resource<User>>

}