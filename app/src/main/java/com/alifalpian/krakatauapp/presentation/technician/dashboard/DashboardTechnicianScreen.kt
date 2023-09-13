package com.alifalpian.krakatauapp.presentation.technician.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.domain.TechnicianDashboardEquipment
import com.alifalpian.krakatauapp.domain.User
import com.alifalpian.krakatauapp.presentation.destinations.LoginScreenDestination
import com.alifalpian.krakatauapp.ui.components.dashboard.DashboardTechnicianEquipmentItem
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauDashboardTopAppBar
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@ExperimentalMaterial3Api
@Composable
fun DashboardTechnicianScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    val user = User(
        id = "user123",
        type = "Teknisi",
        photo = "",
        name = "Hasan Maulana Jhonson",
        division = "Teknisi Divisi SIMK",
        nik = "170904201027"
    )
    val dummyTechnicianDashboardEquipments = (1..10).map {
        TechnicianDashboardEquipment(
            id = it.toString(),
            name = "Komputer",
            count = 654,
            maintenanceCount = 354
        )
    }
    Scaffold(
        topBar = {
            KrakatauDashboardTopAppBar(user = user)
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(items = dummyTechnicianDashboardEquipments, key = { it.id }) {
                DashboardTechnicianEquipmentItem(
                    equipment = it,
                    onClicked = {
                        navigator.navigate(LoginScreenDestination())
                    }
                )
            }
        }
    }
}

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
