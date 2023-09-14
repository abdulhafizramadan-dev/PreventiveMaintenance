package com.alifalpian.krakatauapp.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alifalpian.krakatauapp.R
import com.alifalpian.krakatauapp.presentation.destinations.HomeTechnicianScreenDestination
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauButton
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauOutlinedTextField
import com.alifalpian.krakatauapp.ui.components.krakatau.KrakatauOutlinedTextFieldType
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@ExperimentalFoundationApi
@RootNavGraph(start = true)
@Destination
@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val loginButtonEnabled by loginViewModel.loginButtonEnabled.collectAsState(initial = false)

    val navigateToHomeScreen: () -> Unit = {
        navigator.navigate(HomeTechnicianScreenDestination())
//        navigator.navigate(HomeEmployeeScreenDestination())
    }

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
                value = loginUiState.email,
                onValueChanged = loginViewModel::onEmailChange,
                label = "Email",
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            KrakatauOutlinedTextField(
                value = loginUiState.password,
                onValueChanged = loginViewModel::onPasswordChange,
                label = "Password",
                type = KrakatauOutlinedTextFieldType.Password,
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            KrakatauButton(
                title = "Login",
                onClicked = navigateToHomeScreen,
                enabled = loginButtonEnabled
            )
        }
    }
}

@ExperimentalFoundationApi
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
