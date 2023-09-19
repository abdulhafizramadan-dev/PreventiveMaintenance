package com.alifalpian.krakatauapp.presentation.empoyee.maintenance.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.domain.model.MaintenanceEquipment
import com.alifalpian.krakatauapp.presentation.destinations.MaintenanceFormEmployeeScreenDestination
import com.alifalpian.krakatauapp.presentation.empoyee.maintenance.form.MaintenanceFormEmployeeScreenStatus
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBarWithTabRow
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceEmployeeItem
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ListMaintenanceEmployeeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    val tabPager = listOf(
        "Semua", "Selesai"
    )
    val pagerState = rememberPagerState {
        tabPager.size
    }
    val scope = rememberCoroutineScope()
    val equipments = remember {
        (1..10).map {
            MaintenanceEquipment(
                id = it.toString(),
                order = "2210043175",
                date = "09/12/2023",
                interval = "4 MON",
                execution = "PG IT",
                location = "Ruang Staff SEKPER (WTP)",
                equipmentName = "LAPTOP DELL LATITUDE 3420 SKP4",
                technicianName = "Hasan Maulana"
            )
        }
    }
    val onAllMaintenanceEquipmentClicked: (MaintenanceEquipment) -> Unit = {
        navigator.navigate(MaintenanceFormEmployeeScreenDestination(status = MaintenanceFormEmployeeScreenStatus.Unfinished))
    }

    val onFinishedMaintenanceEquipmentClicked: (MaintenanceEquipment) -> Unit = {
        navigator.navigate(MaintenanceFormEmployeeScreenDestination(status = MaintenanceFormEmployeeScreenStatus.Finish))
    }

    Scaffold(
        topBar = {
            KrakatauTopAppBarWithTabRow(
                title = "Maintenance",
                onNavigationIconClicked = {},
                pagerState = pagerState,
                scope = scope,
                tabs = tabPager
            )
        },
        modifier = modifier
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { page ->
            when (page) {
                0 -> ListMaintenanceEmployeeContent(
                    equipments = equipments,
                    onEquipmentClicked = onAllMaintenanceEquipmentClicked
                )
                1 -> ListMaintenanceEmployeeContent(
                    equipments = equipments,
                    onEquipmentClicked = onFinishedMaintenanceEquipmentClicked
                )
            }
        }
    }
}

@Composable
private fun ListMaintenanceEmployeeContent(
    modifier: Modifier = Modifier,
    equipments: List<MaintenanceEquipment>,
    onEquipmentClicked: (MaintenanceEquipment) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = equipments, key = { it.id }) { equipment ->
            MaintenanceEmployeeItem(
                equipment = equipment,
                onClicked = onEquipmentClicked
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewListMaintenanceEmployeeScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            ListMaintenanceEmployeeScreen()
        }
    }
}
