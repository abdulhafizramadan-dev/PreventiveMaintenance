package com.alifalpian.krakatauapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.R
import com.alifalpian.krakatauapp.ui.components.KrakatauButton
import com.alifalpian.krakatauapp.ui.components.KrakatauOutlinedTextField
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = modifier
                    .width(width = 147.dp)
                    .height(height = 36.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            KrakatauOutlinedTextField(
                value = "",
                onValueChanged = {},
                label = "Email",
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            KrakatauOutlinedTextField(
                value = "",
                onValueChanged = {},
                label = "Password",
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            KrakatauButton(
                title = "Login",
                onClicked = {}
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoginScreen() {
    PreventiveMaintenanceTheme {
        Surface {
            LoginScreen()
        }
    }
}
