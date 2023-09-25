package com.alifalpian.krakatauapp.data.repository

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.MaintenanceCheckPoint
import com.alifalpian.krakatauapp.domain.model.MaintenanceHistory
import com.alifalpian.krakatauapp.domain.model.MaintenanceSafetyUse
import com.alifalpian.krakatauapp.domain.model.MaintenanceTools
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.domain.repository.FirebaseFirestoreRepository
import com.alifalpian.krakatauapp.util.emptyString
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
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
        val user = firestore.collection("users").whereEqualTo("uid", uid).get().await().first().let {
            User(
                documentId = it.id,
                type = it.getString("type") ?: emptyString(),
                photo = it.getString("photo") ?: emptyString(),
                name = it.getString("name") ?: emptyString(),
                division = it.getString("division") ?: emptyString(),
                nik = it.getString("nik") ?: emptyString()
            )
        }
        emit(Resource.Success(user))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getEquipment(equipmentDocumentId: String): Flow<Resource<Equipment>> = flow<Resource<Equipment>> {
        emit(Resource.Loading)
        val equipment = firestore.collection("equipments").document(equipmentDocumentId).get().await().let {
            Equipment(
                documentId = it.id,
                equipment = it.getString("equipment") ?: emptyString(),
                date = it.getDate("date")?.toString() ?: emptyString(),
                interval = it.getString("interval") ?: emptyString(),
                execution = it.getString("execution") ?: emptyString(),
                location = it.getString("location") ?: emptyString(),
                description = it.getString("description") ?: emptyString(),
                type = it.getString("type") ?: emptyString(),
                maintenanceCheckPointType = it.getString("maintenance_check_point_type") ?: emptyString(),
                uid = it.getString("uid") ?: emptyString()
            )
        }
        delay(3000L)
        emit(Resource.Success(equipment))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getTechnicianDashboardEquipments(technicianDocumentId: String): Flow<Resource<List<TechnicianDashboardEquipment>>> = flow<Resource<List<TechnicianDashboardEquipment>>> {
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
                maintenanceCheckPointType = it.getString("maintenance_check_point_type") ?: emptyString(),
                uid = it.getString("uid") ?: emptyString()
            )
        }
        val equipmentTypes = equipments.distinctBy { it.type }
            .map { it.type }
        val technicianDashboardEquipments = equipmentTypes.map {
            TechnicianDashboardEquipment(
                name = it.replaceFirstChar { char -> char.uppercase() },
                count = equipments.count { document -> document.type == it },
                maintenanceCount = firestore.collection("maintenance_history").whereEqualTo("technician_document_id", technicianDocumentId)
                    .whereEqualTo("type", it).get().await().count(),
                id = UUID.randomUUID().toString()
            )
        }
        emit(Resource.Success(technicianDashboardEquipments))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getEquipmentsWillBeMaintenance(technicianDocumentId: String): Flow<Resource<List<Equipment>>> = flow<Resource<List<Equipment>>> {
        emit(Resource.Loading)
        val equipmentsWillBeMaintenance = firestore.collection("equipment_will_maintenance").whereEqualTo("technician_document_id", technicianDocumentId).get().await()
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
                maintenanceCheckPointType = it.getString("maintenance_check_point_type") ?: emptyString(),
                uid = equipmentSnapshot.getString("uid") ?: emptyString()
            )
            equipment
        }
        emit(Resource.Success(equipments))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getEquipmentsHasBeenMaintenance(technicianDocumentId: String): Flow<Resource<List<Equipment>>> = flow<Resource<List<Equipment>>> {
        emit(Resource.Loading)
        val maintenanceHistories = firestore.collection("maintenance_history").whereEqualTo("technician_document_id", technicianDocumentId).get().await()
        val equipments = maintenanceHistories.map {
            val equipmentDocumentId = it.getString("equipment_document_id") ?: ""
            val equipmentSnapshot = firestore.collection("equipments").document(equipmentDocumentId).get().await()
            val equipment = Equipment(
                documentId = equipmentSnapshot.id,
                equipment = equipmentSnapshot.getString("equipment") ?: emptyString(),
                date = equipmentSnapshot.getDate("date")?.toString() ?: emptyString(),
                interval = equipmentSnapshot.getString("interval") ?: emptyString(),
                execution = equipmentSnapshot.getString("execution") ?: emptyString(),
                location = equipmentSnapshot.getString("location") ?: emptyString(),
                description = equipmentSnapshot.getString("description") ?: emptyString(),
                type = equipmentSnapshot.getString("type") ?: emptyString(),
                maintenanceCheckPointType = it.getString("maintenance_check_point_type") ?: emptyString(),
                uid = equipmentSnapshot.getString("uid") ?: emptyString()
            )
            equipment.maintenanceHistoryDocumentId = it.id
            equipment
        }
        emit(Resource.Success(equipments))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getMaintenanceHistory(maintenanceHistoryDocumentId: String): Flow<Resource<MaintenanceHistory>> = flow<Resource<MaintenanceHistory>> {
        emit(Resource.Loading)
        val maintenanceHistory = firestore.collection("maintenance_history").document(maintenanceHistoryDocumentId).get().await().let {
            MaintenanceHistory(
                documentId = it.id,
                technicianDocumentId = it.getString("technician_document_id") ?: emptyString(),
                maintenanceCheckPoint = it.getString("maintenance_check_point") ?: emptyString(),
                equipmentDocumentId = it.getString("equipment_document_id") ?: emptyString(),
                type = it.getString("type") ?: emptyString(),
                date = it.getDate("date")?.toString() ?: emptyString(),
                maintenanceCheckPointHistoryDocumentId = it.getString("maintenance_check_point_history_document_id") ?: emptyString(),
            )
        }
        emit(Resource.Success(maintenanceHistory))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getMaintenanceCheckPoint(checkPointId: String): Flow<Resource<List<MaintenanceCheckPoint>>> = flow<Resource<List<MaintenanceCheckPoint>>> {
        emit(Resource.Loading)
        val maintenanceCheckPoints = firestore.collection("maintenance_check_point").document(checkPointId).get().await().let {
            val checkPoints: List<String> = it.get("check_points") as List<String>
            checkPoints.map { check ->
                MaintenanceCheckPoint(
                    id = UUID.randomUUID().toString(),
                    text = check
                )
            }
        }
        emit(Resource.Success(maintenanceCheckPoints))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getMaintenanceCheckPointHistory(checkPointId: String, maintenanceCheckPointHistoryDocumentId: String): Flow<Resource<List<MaintenanceCheckPoint>>> = flow<Resource<List<MaintenanceCheckPoint>>> {
        emit(Resource.Loading)
        val maintenanceCheckPoints = firestore.collection("maintenance_check_point").document(checkPointId).get().await().let {
            val checkPoints: List<String> = it.get("check_points") as List<String>
            checkPoints
        }
        val maintenanceCheckPointHistory = firestore.collection("maintenance_check_point_history").document(maintenanceCheckPointHistoryDocumentId).get().await().let {
            val checkPoints: List<Boolean> = it.get("maintenance_check_point_history") as List<Boolean>
            checkPoints.mapIndexed { index, check ->
                MaintenanceCheckPoint(
                    id = UUID.randomUUID().toString(),
                    text = maintenanceCheckPoints[index],
                    isChecked = check
                )
            }
        }
        emit(Resource.Success(maintenanceCheckPointHistory))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getMaintenanceTools(maintenanceHistoryDocumentId: String): Flow<Resource<List<MaintenanceTools>>> = flow<Resource<List<MaintenanceTools>>> {
        emit(Resource.Loading)
        val maintenanceTools = firestore.collection("maintenance_tools").whereEqualTo("maintenance_history_document_id", maintenanceHistoryDocumentId).get().await()
            .map {
                MaintenanceTools(
                    documentId = it.id,
                    description = it.getString("description") ?: emptyString(),
                    quantity = it.get("quantity")?.toString()?.toInt() ?: 0,
                    unitOfMeasurement = it.get("unit_of_measurement")?.toString()?.toInt() ?: 0
                )
            }
        emit(Resource.Success(maintenanceTools))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun getMaintenanceSafetyUse(maintenanceHistoryDocumentId: String): Flow<Resource<List<MaintenanceSafetyUse>>> = flow<Resource<List<MaintenanceSafetyUse>>> {
        emit(Resource.Loading)
        val maintenanceTools = firestore.collection("maintenance_safety_use").whereEqualTo("maintenance_history_document_id", maintenanceHistoryDocumentId).get().await()
            .map {
                MaintenanceSafetyUse(
                    documentId = it.id,
                    description = it.getString("description") ?: emptyString(),
                    quantity = it.get("quantity")?.toString()?.toInt() ?: 0,
                    unitOfMeasurement = it.get("unit_of_measurement")?.toString()?.toInt() ?: 0
                )
            }
        emit(Resource.Success(maintenanceTools))
    }.catch {
        emit(Resource.Error(it.message))
    }

    override fun submitMaintenance(
        equipmentDocumentId: String,
        maintenanceCheckPointType: String,
        technicianDocumentId: String,
        equipmentType: String,
        maintenanceCheckPoints: List<MaintenanceCheckPoint>,
        maintenanceTools: List<MaintenanceTools>,
        maintenanceSafetyUse: List<MaintenanceSafetyUse>
    ): Flow<Resource<String>> = flow<Resource<String>> {

        emit(Resource.Loading)

        val maintenanceCheckPointsMap = hashMapOf("maintenance_check_point_history" to maintenanceCheckPoints.map { it.isChecked })
        val maintenanceCheckpointsResponse = firestore.collection("maintenance_check_point_history")
            .add(maintenanceCheckPointsMap).await()
        val maintenanceCheckpointDocumentId = maintenanceCheckpointsResponse.id

        val maintenanceHistoryMap = hashMapOf(
            "date" to FieldValue.serverTimestamp(),
            "equipment_document_id" to equipmentDocumentId,
            "maintenance_check_point" to maintenanceCheckPointType,
            "maintenance_check_point_history_document_id" to maintenanceCheckpointDocumentId,
            "technician_document_id" to technicianDocumentId,
            "type" to equipmentType
        )
        val maintenanceHistoryResponse = firestore.collection("maintenance_history")
            .add(maintenanceHistoryMap).await()
        val maintenanceHistoryDocumentId = maintenanceHistoryResponse.id

        val maintenanceToolsMap = maintenanceTools.map {
            hashMapOf(
                "description" to it.description,
                "maintenance_history_document_id" to maintenanceHistoryDocumentId,
                "quantity" to it.quantity,
                "unit_of_measurement" to it.unitOfMeasurement
            )
        }
        firestore.collection("maintenance_tools")
            .add(maintenanceToolsMap).await()

        val maintenanceSafetyUseMap = maintenanceTools.map {
            hashMapOf(
                "description" to it.description,
                "maintenance_history_document_id" to maintenanceHistoryDocumentId,
                "quantity" to it.quantity,
                "unit_of_measurement" to it.unitOfMeasurement
            )
        }
        firestore.collection("maintenance_safety_use")
            .add(maintenanceSafetyUseMap).await()
    }.catch {
        emit(Resource.Error(it.message))
    }

}