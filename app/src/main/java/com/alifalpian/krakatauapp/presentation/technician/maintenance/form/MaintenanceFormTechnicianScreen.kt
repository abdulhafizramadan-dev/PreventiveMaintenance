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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alifalpian.krakatauapp.domain.MaintenanceTools
import com.alifalpian.krakatauapp.domain.SafetyMaintenance
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
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    viewModel: MaintenanceFormTechnicianViewModel = hiltViewModel()
) {
    val maintenanceFormTechnicianUiState by viewModel.maintenanceFormTechnicianUiState.collectAsState()
    val equipment = maintenanceFormTechnicianUiState.equipment
    val preventiveCheckLists = maintenanceFormTechnicianUiState.preventiveCheckList
    val toolsMaintenanceForm = maintenanceFormTechnicianUiState.toolsMaintenanceForm
    val safetyMaintenanceForm = maintenanceFormTechnicianUiState.safetyMaintenanceForm

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

    val onAddToolsMaintenanceFormClicked: () -> Unit = {
        viewModel.addToolsMaintenanceForm()
    }

    val onAddSafetyMaintenanceForm: () -> Unit = {
        viewModel.addSafetyMaintenanceTools()
    }

    val onToolsMaintenanceFormChanged: (Int, MaintenanceTools) -> Unit = { index, maintenanceTools ->
        viewModel.updateToolsMaintenanceForm(index = index, maintenanceTools = maintenanceTools)
    }

    val onSafetyMaintenanceFormChanged: (Int, SafetyMaintenance) -> Unit = { index, tools ->
        viewModel.updateSafetyMaintenanceForm(index = index, safetyTools = tools)
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
                    if (equipment == null) return@item
                    MaintenanceHeader(
                        equipment = equipment,
                        modifier = Modifier.padding(32.dp)
                    )
                }
                item {
                    if (equipment == null) return@item
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
                                    tools = toolsMaintenanceForm,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceToolsFormType.Technician,
                                    onAddButtonClicked = onAddToolsMaintenanceFormClicked,
                                    onMaintenanceFormChange = onToolsMaintenanceFormChanged
                                )
                            }

                            1 -> {
                                MaintenanceSafetyUseForm(
                                    tools = safetyMaintenanceForm,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceSafetyUseFormType.Technician,
                                    onAddButtonClicked = onAddSafetyMaintenanceForm,
                                    onMaintenanceFormChange = onSafetyMaintenanceFormChanged
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
