package com.alifalpian.krakatauapp.presentation.technician.maintenance.form

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.alifalpian.krakatauapp.domain.model.MaintenanceTools
import com.alifalpian.krakatauapp.domain.model.Resource
import com.alifalpian.krakatauapp.domain.model.MaintenanceSafetyUse
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauButton
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTabRow
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBar
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauTopAppBarType
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceFormCheckList
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceFormHeader
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceHeader
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceSafetyUseForm
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceSafetyUseFormType
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceToolsForm
import com.alifalpian.krakatauapp.ui.components.maintenance.MaintenanceToolsFormType
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

private const val TAG = "MaintenanceFormTechnici"

enum class MaintenanceFormTechnicianScreenStatus {
    Maintenance, History
}

//@RootNavGraph(start = true)
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun MaintenanceFormTechnicianScreen(
    modifier: Modifier = Modifier,
    status: MaintenanceFormTechnicianScreenStatus = MaintenanceFormTechnicianScreenStatus.Maintenance,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    viewModel: MaintenanceFormTechnicianViewModel = hiltViewModel(),
    equipmentDocumentId: String = "T0TvZy3pnDDkFWvH22R7",
    maintenanceHistoryDocumentId: String = "p8j0Twj0rlXvB69SJGLe",
) {
    val maintenanceFormTechnicianUiState by viewModel.maintenanceFormTechnicianUiState.collectAsState()
    val equipment = maintenanceFormTechnicianUiState.equipment
    val user = maintenanceFormTechnicianUiState.user
    val maintenanceHistory = maintenanceFormTechnicianUiState.maintenanceHistory
    val maintenanceCheckPoints = maintenanceFormTechnicianUiState.maintenanceCheckPoints
    val maintenanceToolsForm = maintenanceFormTechnicianUiState.maintenanceToolsForm
    val maintenanceSafetyUseForm = maintenanceFormTechnicianUiState.maintenanceSafetyUseForm

    val tabPages = listOf(
        "Tools/Alat", "Penggunaan Safety"
    )
    val pagerState = rememberPagerState {
        tabPages.size
    }
    val scope = rememberCoroutineScope()

    val editableForm = remember {
        status == MaintenanceFormTechnicianScreenStatus.Maintenance
    }

    LaunchedEffect(key1 = Unit) {
        when (status) {
            MaintenanceFormTechnicianScreenStatus.Maintenance -> {
                viewModel.getEquipment(equipmentDocumentId)
                viewModel.setMaintenanceToolsFormToMaintenanceState()
                viewModel.setMaintenanceSafetyUseFormToMaintenanceState()
            }
            MaintenanceFormTechnicianScreenStatus.History -> viewModel.getMaintenanceHistory(maintenanceHistoryDocumentId)
        }
    }

    LaunchedEffect(key1 = equipment) {
        if (equipment is Resource.Success) {
            viewModel.getUser(equipment.data.uid)
            when (status) {
                MaintenanceFormTechnicianScreenStatus.Maintenance -> viewModel.getMaintenanceCheckPoint(equipment.data.maintenanceCheckPointType)
                MaintenanceFormTechnicianScreenStatus.History -> {
                    if (maintenanceHistory is Resource.Success) {
                        viewModel.getMaintenanceCheckPointHistory(
                            checkPointId = equipment.data.maintenanceCheckPointType,
                            maintenanceCheckPointHistoryDocumentId = maintenanceHistory.data.maintenanceCheckPointHistoryDocumentId
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = maintenanceHistory) {
        if (maintenanceHistory is Resource.Success) {
            viewModel.getEquipment(maintenanceHistory.data.equipmentDocumentId)
            viewModel.getMaintenanceTools(maintenanceHistory.data.documentId)
            viewModel.getMaintenanceSafetyUse(maintenanceHistory.data.documentId)
        }
    }

//    LaunchedEffect(key1 = user) {
//        when (user) {
//            Resource.Idling -> {}
//            Resource.Loading -> Log.d(TAG, "MaintenanceFormTechnicianScreen: Loading...")
//            is Resource.Error -> Log.d(
//                TAG,
//                "MaintenanceFormTechnicianScreen: Error = ${user.error}"
//            )
//            is Resource.Success -> Log.d(
//                TAG,
//                "MaintenanceFormTechnicianScreen: Success = ${user.data}"
//            )
//        }
//    }

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

    val onSafetyMaintenanceFormChanged: (Int, MaintenanceSafetyUse) -> Unit = { index, tools ->
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
                .animateContentSize()
                .background(Color(0xFFEDEDED))
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    MaintenanceHeader(
                        equipment = equipment,
                        user = user,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    )
                }
                item {
                    MaintenanceFormHeader(
                        equipment = equipment,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(top = 16.dp)
                    )
                }
                item {
                    MaintenanceFormCheckList(
                        maintenanceCheckPoints = maintenanceCheckPoints,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .padding(bottom = 20.dp),
                        checkListEnabled = editableForm,
                        onCheckedChanged = viewModel::updateMaintenanceCheckPoints
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
                    HorizontalPager(state = pagerState, verticalAlignment = Alignment.Top) { position ->
                        when (position) {
                            0 -> {
                                MaintenanceToolsForm(
                                    maintenanceTools = maintenanceToolsForm,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceToolsFormType.Technician,
                                    onAddButtonClicked = onAddToolsMaintenanceFormClicked,
                                    onMaintenanceFormChange = onToolsMaintenanceFormChanged,
                                    enabled = editableForm
                                )
                            }

                            1 -> {
                                MaintenanceSafetyUseForm(
                                    maintenanceSafetyUse = maintenanceSafetyUseForm,
                                    modifier = Modifier.padding(32.dp),
                                    type = MaintenanceSafetyUseFormType.Technician,
                                    onAddButtonClicked = onAddSafetyMaintenanceForm,
                                    onMaintenanceFormChange = onSafetyMaintenanceFormChanged,
                                    enabled = editableForm
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
                enabled = editableForm
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
