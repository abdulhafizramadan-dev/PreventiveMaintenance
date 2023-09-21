package com.alifalpian.krakatauapp.domain.repository

import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FirebaseFirestoreRepository {

    fun getUser(uid: String): Flow<Resource<User>>

    fun getTechnicianDashboardEquipments(uid: String): Flow<Resource<List<TechnicianDashboardEquipment>>>

    fun getEquipmentsWillBeMaintenance(uid: String): Flow<Resource<List<Equipment>>>

}