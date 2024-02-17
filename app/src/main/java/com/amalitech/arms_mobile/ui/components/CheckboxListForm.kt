package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class CheckBoxListItemData(
    val text: String,
    val condition: (List<String>) -> Boolean,
    val onChecked: (List<String>, Boolean) -> List<String>
)

@Composable
fun CheckboxListForm(
    selectedValues: List<String>,
    submitButtonText: String,
    submit: (List<String>) -> Unit,
    items: List<CheckBoxListItemData>
) {
    var values by remember { mutableStateOf(selectedValues) }

    Column {
        Text(
            "Select your preferred options",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        items.map {
            CheckBoxListItem(
                text = it.text,
                value = it.condition(values),
                onChecked = { value ->
                    values = it.onChecked(values, value)
                },
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffDD5928),
                contentColor = Color.White,
            ),
            onClick = { submit(values) },
        ) {
            Text(
                submitButtonText,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

    }
}


@Composable
private fun CheckBoxListItem(
    modifier: Modifier = Modifier,
    text: String,
    value: Boolean = false,
    onChecked: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text)
        Checkbox(
            checked = value, onCheckedChange = onChecked,
            colors = CheckboxDefaults.colors(
                checkmarkColor = Color.White, checkedColor = Color(0xffDD5928)
            ),
        )
    }
}