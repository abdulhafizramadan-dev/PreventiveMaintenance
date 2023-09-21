package com.alifalpian.krakatauapp.presentation.technician.dashboard

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.presentation.destinations.LoginScreenDestination
import com.alifalpian.krakatauapp.ui.components.dashboard.DashboardTechnicianEquipmentItem
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauDashboardTopAppBar
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

private const val TAG = "DashboardTechnicianScre"

@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun DashboardTechnicianScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    dashboardTechnicianViewModel: DashboardTechnicianViewModel = hiltViewModel(),
    uid: String = "E1jlNeppUxgSxnw9Rh7XYx0cWO93"
) {

    val dashboardTechnicianUiState by dashboardTechnicianViewModel.dashboardTechnicianUiState.collectAsState()
    val dashboardEquipments = dashboardTechnicianUiState.dashboardEquipments
    val user = dashboardTechnicianUiState.user

    LaunchedEffect(key1 = Unit) {
        dashboardTechnicianViewModel.getUser(uid)
        dashboardTechnicianViewModel.getTechnicianDashboardEquipments(uid)
    }

    Scaffold(
        topBar = {
            KrakatauDashboardTopAppBar(user = user)
        },
        modifier = modifier
    ) { paddingValues ->
        DashboardTechnicianContent(
            modifier = Modifier.padding(paddingValues),
            dashboardEquipments = dashboardEquipments
        )
    }
}

@Composable
fun DashboardTechnicianContent(
    modifier: Modifier = Modifier,
    dashboardEquipments: Resource<List<TechnicianDashboardEquipment>>,
    onItemClicked: () -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        when (dashboardEquipments) {
            Resource.Idling -> {}
            Resource.Loading -> {}
            is Resource.Error -> Log.d(
                TAG,
                "DashboardTechnicianContent: Error = ${dashboardEquipments.error}"
            )
            is Resource.Success -> {
                items(items = dashboardEquipments.data, key = { it.id }) {
                    DashboardTechnicianEquipmentItem(
                        equipment = it,
                        onClicked = { onItemClicked() }
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDashboardTechnicianScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            DashboardTechnicianScreen()
        }
    }
}
