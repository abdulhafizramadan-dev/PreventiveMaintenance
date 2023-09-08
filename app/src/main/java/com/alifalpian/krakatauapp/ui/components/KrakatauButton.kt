package com.alifalpian.krakatauapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

@Composable
fun KrakatauButton(
    modifier: Modifier = Modifier,
    title: String,
    onClicked: () -> Unit
) {
    Button(
        onClick = onClicked,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 1.dp),
    ) {
        Text(text = title)
    }
}

@Preview
@Composable
fun PreviewKrakatauButton() {
    PreventiveMaintenanceTheme {
        Surface {
            KrakatauButton(
                title = "Login",
                onClicked = {},
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
