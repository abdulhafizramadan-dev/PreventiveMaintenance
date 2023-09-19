package com.alifalpian.krakatauapp.ui.components.krakatau

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.alifalpian.krakatauapp.R
import com.alifalpian.krakatauapp.domain.model.User
import com.alifalpian.krakatauapp.ui.theme.PreventiveMaintenanceTheme

@Composable
fun KrakatauDashboardTopAppBar(
    modifier: Modifier = Modifier,
    user: User
) {
    Box(
        modifier = modifier
            .height(98.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_dashboard_top_app_bar),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = 30.dp, top = 24.dp)
            ) {
                Text(
                    text = user.name,
                    color = MaterialTheme.colorScheme.background,
                    lineHeight = 2.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.division,
                    color = MaterialTheme.colorScheme.background,
                    lineHeight = 2.33.em,
                    style = TextStyle(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.nik,
                    color = MaterialTheme.colorScheme.background,
                    lineHeight = 2.33.em,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Row(
                modifier = Modifier.offset(y = 8.dp)
                    .clip(RoundedCornerShape(size = 20.dp))
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(start = 8.dp, top = 4.dp, end = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user.type,
                    lineHeight = 2.33.em,
                    style = TextStyle(fontSize = 12.sp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = "IMG Profile",
                    modifier = modifier
                        .size(25.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(22.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewKrakatauDashboardTopAppBar() {
    PreventiveMaintenanceTheme {
        Surface {
            val user = User(
                id = "user123",
                type = "Teknisi",
                photo = "",
                name = "Hasan Maulana Jhonson",
                division = "Teknisi Divisi SIMK",
                nik = "170904201027"
            )
            Scaffold(
                topBar = {
                    KrakatauDashboardTopAppBar(user = user)
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}