package com.alifalpian.krakatauapp.ui.navigation

sealed class KrakatauAppScreens(
    val route: String
) {
    object HomeScreen : KrakatauAppScreens("home")
    object DashboardScreen : KrakatauAppScreens("dashboard")
    object MaintenanceScreen : KrakatauAppScreens("maintenance")
    object HistoryScreen : KrakatauAppScreens("history")
    object LoginScreen : KrakatauAppScreens("login")
}
