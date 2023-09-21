package com.alifalpian.krakatauapp.data.repository

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.domain.repository.FirebaseFirestoreRepository
import com.alifalpian.krakatauapp.util.emptyString
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID
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

    override fun getTechnicianDashboardEquipments(uid: String): Flow<Resource<List<TechnicianDashboardEquipment>>> = flow<Resource<List<TechnicianDashboardEquipment>>> {
        emit(Resource.Loading)
        val equipments = firestore.collection("equipments").get().await().map {
            Equipment(
                documentId = it.id,
                equipment = it.getString("equipment") ?: emptyString(),
                date = it.getDate("date")?.toString() ?: emptyString(),
                interval = it.getString("interval") ?: emptyString(),
                execution = it.getString("execution") ?: emptyString(),
                location = it.getString("location") ?: emptyString(),
                description = it.getString("description") ?: emptyString(),
                type = it.getString("type") ?: emptyString(),
                uid = it.getString("uid") ?: emptyString()
            )
        }
        val equipmentTypes = equipments.distinctBy { it.type }
            .map { it.type }
        val technicianDashboardEquipments = equipmentTypes.map {
            TechnicianDashboardEquipment(
                name = it.replaceFirstChar { char -> char.uppercase() },
                count = equipments.count { document -> document.type == it },
                maintenanceCount = firestore.collection("maintenance_history").whereEqualTo("uid", uid)
                    .whereEqualTo("type", it).get().await().count(),
                id = UUID.randomUUID().toString()
            )
        }
        emit(Resource.Success(technicianDashboardEquipments))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getEquipmentsWillBeMaintenance(uid: String): Flow<Resource<List<Equipment>>> = flow<Resource<List<Equipment>>> {
        emit(Resource.Loading)
        val equipmentsWillBeMaintenance = firestore.collection("equipment_will_maintenance").whereEqualTo("uid", uid).get().await()
        val equipments = equipmentsWillBeMaintenance.map {
            val equipmentId = it.getString("equipment")
            val equipmentSnapshot = firestore.collection("equipments").whereEqualTo("equipment", equipmentId).limit(1).get().await().first()
            val equipment = Equipment(
                documentId = equipmentSnapshot.id,
                equipment = equipmentSnapshot.getString("equipment") ?: emptyString(),
                date = equipmentSnapshot.getDate("date")?.toString() ?: emptyString(),
                interval = equipmentSnapshot.getString("interval") ?: emptyString(),
                execution = equipmentSnapshot.getString("execution") ?: emptyString(),
                location = equipmentSnapshot.getString("location") ?: emptyString(),
                description = equipmentSnapshot.getString("description") ?: emptyString(),
                type = equipmentSnapshot.getString("type") ?: emptyString(),
                uid = equipmentSnapshot.getString("uid") ?: emptyString()
            )
            equipment
        }
        emit(Resource.Success(equipments))
    }.catch {
        emit(Resource.Error(it.message))
    }
}