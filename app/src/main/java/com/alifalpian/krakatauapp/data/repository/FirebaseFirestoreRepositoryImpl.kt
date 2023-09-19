package com.alifalpian.krakatauapp.data.repository

import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.domain.repository.FirebaseFirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirebaseFirestoreRepository {
    override fun getUser(uid: String): Flow<Resource<User>> = flow<Resource<User>> {
        emit(Resource.Loading)
        val user = firestore.collection("users").whereEqualTo("uid", uid).get().await().first()
            .toObject<User>()
        emit(Resource.Success(user))
    }.catch {
        emit(Resource.Error(it.message))
    }
}