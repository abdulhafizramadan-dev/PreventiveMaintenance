package com.alifalpian.krakatauapp.presentation.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alifalpian.krakatauapp.domain.MaintenanceEquipment
import com.alifalpian.krakatauapp.domain.PreventiveCheckList
import com.alifalpian.krakatauapp.ui.components.KrakatauOutlinedTextField
import com.alifalpian.krakatauapp.ui.components.PreventiveCheckListItem
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

enum class MaintenanceContentType {
    Technician, Employee
}

@ExperimentalMaterial3Api
@Composable
fun MaintenanceContent(
    modifier: Modifier = Modifier,
    equipment: MaintenanceEquipment,
    preventiveCheckLists: List<PreventiveCheckList>,
    type: MaintenanceContentType = MaintenanceContentType.Technician
) {
    val checkListEnabled = remember {
        type == MaintenanceContentType.Technician
    }
    Column(
        modifier = Modifier
            .background(color = Color(0xFFEDEDED))
            .then(modifier)
    ) {
        Text(
            text = "RESULT PREVENTIVE MAINTENANCE",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        MaintenanceContentRowItem(label = "EQUIPMENT", text = equipment.id)
        Spacer(modifier = Modifier.height(8.dp))
        MaintenanceContentRowItem(label = "ALAT/KODE", text = equipment.equipmentName)
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "URAIAN PEKERJAAN :",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "PREVENTIVE PC DAN LAPTOP",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        preventiveCheckLists.forEachIndexed { index, preventiveCheckList ->
            Spacer(modifier = Modifier.height(4.dp))
            PreventiveCheckListItem(
                item = preventiveCheckList,
                onCheckedChange = {},
                modifier = Modifier.padding(horizontal = 8.dp),
                enabled = checkListEnabled
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (index != preventiveCheckLists.lastIndex) {
                Divider()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "DURASI PLANT : 1.0",
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "DURASI AKTUAL : 3.0",
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        if (type == MaintenanceContentType.Technician) {
            KrakatauOutlinedTextField(value = "", onValueChanged = {})
        }
    }
}

@Composable
private fun MaintenanceContentRowItem(
    label: String,
    text: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )
        Text(text = ":")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.weight(3f),
            lineHeight = 16.sp
        )
    }
}



@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewMaintenanceContent() {
    PreventiveMaintenanceTheme {
        Surface {
            val equipment = MaintenanceEquipment(
                id = "12319814917",
                order = "2210043175",
                date = "09/12/2023",
                interval = "4 MON",
                execution = "PG IT",
                location = "Ruang Staff SEKPER (WTP)",
                equipmentName = "LAPTOP DELL LATITUDE 3420 SKP4",
                technicianName = "Hasan Maulana"
            )
            val preventiveCheckLists = (1..10).map {
                PreventiveCheckList(
                    id = it.toString(),
                    text = "Periksa komponen komputer pastikan tidak ada kerusakan"
                )
            }
            MaintenanceContent(
                modifier = Modifier.padding(16.dp),
                equipment = equipment,
                preventiveCheckLists = preventiveCheckLists
            )
        }
    }
}
