package com.alifalpian.krakatauapp.presentation.maintenance

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.domain.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.MaintenanceTools
import com.alifalpian.krakatauapp.domain.PreventiveCheckList
import com.alifalpian.krakatauapp.ui.components.KrakatauTabRow
import com.alifalpian.krakatauapp.ui.components.KrakatauTopAppBar
import com.alifalpian.krakatauapp.ui.components.KrakatauTopAppBarType
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

enum class MaintenanceScreenType {
    Technician, Employee
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MaintenanceScreen(
    modifier: Modifier = Modifier,
    type: MaintenanceScreenType = MaintenanceScreenType.Technician
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

    Scaffold(
        topBar = {
            KrakatauTopAppBar(
                title = "Maintenance",
                type = KrakatauTopAppBarType.Detail
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
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
                                    type = type
                                )
                            }

                            1 -> {
                                MaintenanceSafetyUseForm(
                                    tools = tools,
                                    modifier = Modifier.padding(32.dp),
                                    type = type
                                )
                            }
                        }
                    }
                }
            }
            MaintenanceFooter(
                type = type,
                modifier = Modifier.padding(18.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewMaintenanceScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            MaintenanceScreen()
        }
    }
}
