package com.amalitech.arms_mobile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.ui.theme.primaryColor
import com.amalitech.arms_mobile.ui.theme.textColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppBottomSheet() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheetM3(
    drawableId: Int,
    title: String, subTitle: String,
    btnTitle: String, mainBtnTitle: String,
) {
    var openSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    Button(
        onClick = { openSheet = true },
        colors = ButtonDefaults.buttonColors(primaryColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
    ) {
        Text(text = mainBtnTitle)
    }

    if (openSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openSheet = false },
            dragHandle = {}

        ) {
            BottomSheetContent(
                title = title,
                subTitle = subTitle,
                drawableId = drawableId,
                btnTitle = btnTitle,
                onHideButtonClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) openSheet = false
                    }
                }
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    onHideButtonClick: () -> Unit,
    drawableId: Int,
    title: String, subTitle: String,
    btnTitle: String
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = subTitle,
            style = TextStyle(
                color = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(primaryColor),
            modifier = Modifier.fillMaxWidth(),
            onClick = onHideButtonClick
        )
        {
            Text(text = btnTitle)
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

