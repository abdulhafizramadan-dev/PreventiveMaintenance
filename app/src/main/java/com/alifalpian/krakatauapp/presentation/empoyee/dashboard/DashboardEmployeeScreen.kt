package com.alifalpian.krakatauapp.presentation.empoyee.dashboard

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.domain.model.EmployeeDashboardEquipment
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.presentation.destinations.LoginScreenDestination
import com.alifalpian.krakatauapp.ui.components.dashboard.DashboardEmployeeEquipmentItem
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauDashboardTopAppBar
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@ExperimentalFoundationApi
@Destination
@ExperimentalMaterial3Api
@Composable
fun DashboardEmployeeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    val equipments = (1..10).map {
        EmployeeDashboardEquipment(
            id = it.toString(),
            order = "2210003221",
            location = "Ruang Staff SEKPER (WTP)",
            equipmentName = "PRINTER INKJET EPSON L3250 SIMK"
        )
    }

    val onLogoutClicked: () -> Unit = {
        navigator.navigate(LoginScreenDestination())
    }
    val onEquipmentClicked: (EmployeeDashboardEquipment) -> Unit = {
        onLogoutClicked()
    }

    Scaffold(
        topBar = {
//            KrakatauDashboardTopAppBar(user = user)
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(items = equipments, key = { it.id }) {
                DashboardEmployeeEquipmentItem(
                    equipment = it,
                    onClicked = onEquipmentClicked
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDashboardEmployeeScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            DashboardEmployeeScreen()
        }
    }
}
