package com.alifalpian.krakatauapp.presentation.technician.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alifalpian.krakatauapp.presentation.technician.dashboard.DashboardTechnicianScreen
import com.alifalpian.krakatauapp.presentation.technician.history.HistoryTechnicianScreen
import com.alifalpian.krakatauapp.presentation.technician.maintenance.list.ListMaintenanceTechnicianScreen
import com.alifalpian.krakatauapp.ui.navigation.KrakatauAppScreens
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

private enum class HomeScreen(
    val route: String,
    val imageVector: ImageVector,
    val label: String
) {
    Dashboard("dashboard", Icons.Default.Home, "Dashboard"),
    Maintenance("maintenance", Icons.Default.Home, "Maintenance"),
    History("history", Icons.Default.Home, "History"),
}

@Destination(start = true)
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            KrakatauNavigationBar(navController)
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = KrakatauAppScreens.DashboardScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(KrakatauAppScreens.DashboardScreen.route) {
                DashboardTechnicianScreen(navigator = navigator)
            }
            composable(KrakatauAppScreens.MaintenanceScreen.route) {
                ListMaintenanceTechnicianScreen(navigator = navigator)
            }
            composable(KrakatauAppScreens.HistoryScreen.route) {
                HistoryTechnicianScreen(navigator = navigator)
            }
        }
    }
}

@Composable
fun KrakatauNavigationBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar {
        HomeScreen.values().forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    Icon(
                        imageVector = screen.imageVector,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = screen.label)
                }
            )
        }
    }
}

