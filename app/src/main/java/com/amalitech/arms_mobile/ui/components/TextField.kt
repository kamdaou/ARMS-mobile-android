package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R

@Composable
fun OutlineBorderTextFormField(text: String) {
    val textState = remember {
        mutableStateOf("")
    }
    Row {
        Text(text = text)
        Text(text = "*", color = Color.Red)
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = text) },
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(4.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = ""
            )
        }

    )
}