package com.alifalpian.krakatauapp.presentation.technician.maintenance.list

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alifalpian.krakatauapp.domain.model.Equipment
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.presentation.destinations.MaintenanceFormTechnicianScreenDestination
import com.alifalpian.krakatauapp.presentation.destinations.StartQuestionMaintenanceScreenDestination
import com.alifalpian.krakatauapp.presentation.technician.maintenance.form.MaintenanceFormTechnicianScreenStatus
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBarWithTabRow
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceTechnicianItem
import com.alifalpian.krakatauapp.ui.components.maintenance.ShimmerMaintenanceTechnicianItem
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

private const val TAG = "ListMaintenanceTechnici"

//@RootNavGraph(start = true)
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ListMaintenanceTechnicianScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    listMaintenanceTechnicianViewModel: ListMaintenanceTechnicianViewModel = hiltViewModel(),
) {
    val tabPager = listOf(
        "Semua", "Selesai"
    )
    val pagerState = rememberPagerState {
        tabPager.size
    }
    val scope = rememberCoroutineScope()

    val listMaintenanceTechnicianUiState by listMaintenanceTechnicianViewModel.listMaintenanceTechnicianUiState.collectAsState()
    val equipmentsWillMaintenance = listMaintenanceTechnicianUiState.equipmentsWillMaintenance
    val equipmentsHasBeenMaintenance = listMaintenanceTechnicianUiState.equipmentsHasBeenMaintenance

    LaunchedEffect(key1 = Unit) {
        listMaintenanceTechnicianViewModel.getEquipmentsWillBeMaintenance("NMafmmi08rDryW2jzMGY")
        listMaintenanceTechnicianViewModel.getEquipmentsHasBeenMaintenance("NMafmmi08rDryW2jzMGY")
    }

    val onAllMaintenanceEquipmentClicked: (Equipment) -> Unit = {
        navigator.navigate(StartQuestionMaintenanceScreenDestination(it.documentId))
    }

    val onFinishedMaintenanceEquipmentClicked: (Equipment) -> Unit = {
        navigator.navigate(MaintenanceFormTechnicianScreenDestination(status = MaintenanceFormTechnicianScreenStatus.History, equipmentDocumentId = it.maintenanceHistoryDocumentId))
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
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { page ->
            when (page) {
                0 -> ListMaintenanceTechnicianContent(
                    equipments = equipmentsWillMaintenance,
                    onEquipmentClicked = onAllMaintenanceEquipmentClicked
                )
                1 -> ListMaintenanceTechnicianContent(
                    equipments = equipmentsHasBeenMaintenance,
                    onEquipmentClicked = onFinishedMaintenanceEquipmentClicked
                )
            }
        }
    }
}

@Composable
private fun ListMaintenanceTechnicianContent(
    modifier: Modifier = Modifier,
    equipments: Resource<List<Equipment>>,
    onEquipmentClicked: (Equipment) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (equipments is Resource.Loading) {
            items(10) {
                ShimmerMaintenanceTechnicianItem()
            }
        }
        if (equipments is Resource.Success) {
            items(items = equipments.data, key = { it.documentId }) { equipment ->
                MaintenanceTechnicianItem(
                    equipment = equipment,
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
fun PreviewListMaintenanceTechnicianScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            ListMaintenanceTechnicianScreen()
        }
    }
}
