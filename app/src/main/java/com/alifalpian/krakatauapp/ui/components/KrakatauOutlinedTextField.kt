package com.alifalpian.krakatauapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alifalpian.krakatauapp.util.emptyString
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

@ExperimentalMaterial3Api
@Composable
fun KrakatauOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String = emptyString(),
    label: String = emptyString()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = label,
                color = Color(0xff4d4d4d)
            )
        },
        placeholder = { Text(placeholder) },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xff1d1b20),
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)))
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun TextFieldPreview() {
    PreventiveMaintenanceTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            KrakatauOutlinedTextField(
                value = value,
                onValueChanged = { value = it },
                placeholder = "Email",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}