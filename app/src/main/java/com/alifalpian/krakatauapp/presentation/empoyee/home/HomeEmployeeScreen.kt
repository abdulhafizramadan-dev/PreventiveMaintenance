package com.alifalpian.krakatauapp.presentation.empoyee.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alifalpian.krakatauapp.R
import com.alifalpian.krakatauapp.domain.model.BottomNavigationItem
import com.alifalpian.krakatauapp.presentation.empoyee.dashboard.DashboardEmployeeScreen
import com.alifalpian.krakatauapp.presentation.empoyee.maintenance.list.ListMaintenanceEmployeeScreen
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauNavigationBar
import com.alifalpian.krakatauapp.ui.navigation.KrakatauAppScreens
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.alifalpian.krakatauapp.util.emptyString
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

//@RootNavGraph(start = true)
@Destination
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeEmployeeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
) {
    val navController = rememberNavController()
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem("dashboard", R.drawable.ic_menu_home, "Dashboard"),
            BottomNavigationItem("maintenance", R.drawable.ic_menu_maintenance, "Maintenance"),
        )
    }

    Scaffold(
        bottomBar = {
            KrakatauNavigationBar(navController, bottomNavigationItems)
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = KrakatauAppScreens.DashboardScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(KrakatauAppScreens.DashboardScreen.route) {
                DashboardEmployeeScreen()
            }
            composable(KrakatauAppScreens.MaintenanceScreen.route) {
                ListMaintenanceEmployeeScreen(navigator = navigator)
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHomeEmployeeScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            HomeEmployeeScreen()
        }
    }
}


