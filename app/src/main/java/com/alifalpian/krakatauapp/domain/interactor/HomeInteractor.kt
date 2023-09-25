package com.alifalpian.krakatauapp.domain.interactor

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.MaintenanceCheckPoint
import com.alifalpian.krakatauapp.domain.model.MaintenanceHistory
import com.alifalpian.krakatauapp.domain.model.MaintenanceSafetyUse
import com.alifalpian.krakatauapp.domain.model.MaintenanceTools
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

    override fun getEquipment(equipmentDocumentId: String): Flow<Resource<Equipment>> {
        return firebaseFirestoreRepository.getEquipment(equipmentDocumentId)
    }

    override fun getTechnicianDashboardEquipments(technicianDocumentId: String): Flow<Resource<List<TechnicianDashboardEquipment>>> {
        return firebaseFirestoreRepository.getTechnicianDashboardEquipments(technicianDocumentId)
    }

    override fun getEquipmentsWillBeMaintenance(technicianDocumentId: String): Flow<Resource<List<Equipment>>> {
        return firebaseFirestoreRepository.getEquipmentsWillBeMaintenance(technicianDocumentId)
    }

    override fun getEquipmentsHasBeenMaintenance(technicianDocumentId: String): Flow<Resource<List<Equipment>>> {
        return firebaseFirestoreRepository.getEquipmentsHasBeenMaintenance(technicianDocumentId)
    }

    override fun getMaintenanceHistory(maintenanceHistoryDocumentId: String): Flow<Resource<MaintenanceHistory>> {
        return firebaseFirestoreRepository.getMaintenanceHistory(maintenanceHistoryDocumentId)
    }

    override fun getMaintenanceCheckPoint(checkPointId: String): Flow<Resource<List<MaintenanceCheckPoint>>> {
        return firebaseFirestoreRepository.getMaintenanceCheckPoint(checkPointId)
    }

    override fun getMaintenanceCheckPointHistory(checkPointId: String, maintenanceCheckPointHistoryDocumentId: String): Flow<Resource<List<MaintenanceCheckPoint>>> {
        return firebaseFirestoreRepository.getMaintenanceCheckPointHistory(checkPointId, maintenanceCheckPointHistoryDocumentId)
    }

    override fun getMaintenanceTools(maintenanceHistoryDocumentId: String): Flow<Resource<List<MaintenanceTools>>> {
        return firebaseFirestoreRepository.getMaintenanceTools(maintenanceHistoryDocumentId)
    }

    override fun getMaintenanceSafetyUse(maintenanceHistoryDocumentId: String): Flow<Resource<List<MaintenanceSafetyUse>>> {
        return firebaseFirestoreRepository.getMaintenanceSafetyUse(maintenanceHistoryDocumentId)
    }
}