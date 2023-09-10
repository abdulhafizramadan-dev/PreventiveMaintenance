package com.alifalpian.krakatauapp.presentation.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alifalpian.krakatauapp.ui.components.KrakatauButton

@Composable
fun MaintenanceFooter(
    modifier: Modifier = Modifier,
    type: MaintenanceScreenType = MaintenanceScreenType.Technician
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color(0xFFE3E3E3))
            .then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (type) {
            MaintenanceScreenType.Technician -> {
                KrakatauButton(title = "Submit", onClicked = {})
            }
            MaintenanceScreenType.Employee -> {
                KrakatauButton(title = "Accept", onClicked = {})
                KrakatauButton(
                    title = "Reject",
                    onClicked = {},
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}