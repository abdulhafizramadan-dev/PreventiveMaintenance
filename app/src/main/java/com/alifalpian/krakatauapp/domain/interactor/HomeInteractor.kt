package com.alifalpian.krakatauapp.domain.interactor

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
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

    override fun getTechnicianDashboardEquipments(uid: String): Flow<Resource<List<TechnicianDashboardEquipment>>> {
        return firebaseFirestoreRepository.getTechnicianDashboardEquipments(uid)
    }

    override fun getEquipmentsWillBeMaintenance(uid: String): Flow<Resource<List<Equipment>>> {
        return firebaseFirestoreRepository.getEquipmentsWillBeMaintenance(uid)
    }

}