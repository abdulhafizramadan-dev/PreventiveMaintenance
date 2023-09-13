package com.alifalpian.krakatauapp.presentation.technician.maintenance.form

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.domain.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.MaintenanceTools
import com.alifalpian.krakatauapp.domain.PreventiveCheckList
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauButton
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTabRow
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBar
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBarType
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceContent
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceContentType
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceHeader
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceSafetyUseForm
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceSafetyUseFormType
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceToolsForm
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceToolsFormType
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

enum class MaintenanceFormTechnicianScreenStatus {
    Unfinished, Finish
}

@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MaintenanceFormTechnicianScreen(
    modifier: Modifier = Modifier,
    status: MaintenanceFormTechnicianScreenStatus = MaintenanceFormTechnicianScreenStatus.Unfinished,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    val equipment = MaintenanceEquipment(
        id = "user123",
        order = "2210043175",
        date = "09/12/2023",
        interval = "4 MON",
        execution = "PG IT",
        location = "Ruang Staff SEKPER (WTP)",
        equipmentName = "LAPTOP DELL LATITUDE 3420 SKP4",
        technicianName = "Hasan Maulana"
    )
    val preventiveCheckLists = (1..10).map {
        PreventiveCheckList(
            id = it.toString(),
            text = "Periksa komponen komputer pastikan tidak ada kerusakan"
        )
    }
    val tools = (1..3).map {
        MaintenanceTools(
            id = it.toString(),
            description = "Obeng",
            quantity = 1,
            unitOfMeasurement = 1
        )
    }

    val tabPages = listOf(
        "Tools/Alat", "Penggunaan Safety"
    )
    val pagerState = rememberPagerState {
        tabPages.size
    }
    val scope = rememberCoroutineScope()

    val buttonSubmitEnabled = remember {
        status == MaintenanceFormTechnicianScreenStatus.Unfinished
    }

    val onNavigationIconClicked: () -> Unit = {
        navigator.navigateUp()
    }

    Scaffold(
        topBar = {
            KrakatauTopAppBar(
                title = "Maintenance",
                type = KrakatauTopAppBarType.Detail,
                onNavigationIconClicked = onNavigationIconClicked
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFEDEDED))
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    MaintenanceHeader(
                        equipment = equipment,
                        modifier = Modifier.padding(32.dp)
                    )
                }
                item {
                    MaintenanceContent(
                        equipment = equipment,
                        preventiveCheckLists = preventiveCheckLists,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                        type = MaintenanceContentType.Technician
                    )
                }
                stickyHeader {
                    KrakatauTabRow(
                        pagerState = pagerState,
                        scope = scope,
                        tabs = tabPages
                    )
                }
                item {
                    HorizontalPager(state = pagerState) { position ->
                        when (position) {
                            0 -> {
                                MaintenanceToolsForm(
                                    tools = tools,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceToolsFormType.Technician
                                )
                            }

                            1 -> {
                                MaintenanceSafetyUseForm(
                                    tools = tools,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceSafetyUseFormType.Technician
                                )
                            }
                        }
                    }
                }
            }
            Divider()
            KrakatauButton(
                title = "Submit",
                onClicked = {},
                modifier = Modifier
                    .padding(18.dp)
                    .align(Alignment.CenterHorizontally),
                enabled = buttonSubmitEnabled
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewMaintenanceFormTechnicianScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            MaintenanceFormTechnicianScreen()
        }
    }
}
