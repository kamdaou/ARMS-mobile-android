package com.example.frontend_masters_tut

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.composables.AppSucessModalSheet
import com.amalitech.arms_mobile.composables.OutlineBorderTextFormField

@Preview(showBackground = true, widthDp = 360, heightDp = 810)
@Composable
fun HelpScreen() {
    Scaffold { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(18.dp)) {
            Row {
                Icon(
                    tint = Color.Gray,
                    painter = painterResource(id = R.drawable.alarm),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = "Report an issue",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            OutlineBorderTextFormField(text = "Full name")
            Spacer(modifier = Modifier.height(20.dp))
            OutlineBorderTextFormField(text = "Phone Number")
            Spacer(modifier = Modifier.height(20.dp))
            OutlineMultiTextField()
            Spacer(modifier = Modifier.height(24.dp))
            AppSucessModalSheet()
        }

    }
}

@Composable
fun OutlineMultiTextField() {
    Row {
        Text(text = "Report")
        Text(text = "*", color = Color.Red)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        value = "full name",
        onValueChange = {},
        maxLines = 4,
    )
}

@Composable
fun ActionButton() {
}

