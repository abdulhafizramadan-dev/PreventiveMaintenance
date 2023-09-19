package com.alifalpian.krakatauapp.domain.repository

import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseFirestoreRepository {

    fun getUser(uid: String): Flow<Resource<User>>

}