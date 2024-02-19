package com.amalitech.arms_mobile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.utilities.AuthenticationException
import com.amalitech.arms_mobile.ui.theme.primaryColor
import kotlinx.coroutines.launch

data class BottomSheetContent(
    var title: String,
    var drawable: Int,
    var content: String,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalSheet(
    onClickAction: () -> Unit,
) {
    var openSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable {
                openSheet = true
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.message_text),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Help")
    }

    if (openSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            onDismissRequest = { openSheet = false },
            dragHandle = {}

        ) {
            BottomSheetContent(
                onHideButtonClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }.invokeOnCompletion {
                        if (!bottomSheetState.isVisible) openSheet = false
                    }
                },
            )
        }
    }
}


@Composable
fun BottomSheetContent(
    onHideButtonClick: () -> Unit,
//    onClickAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        Icon(
            tint = Color.DarkGray,
            modifier = Modifier
                .clickable { onHideButtonClick() }
                .size(36.dp),
            painter = painterResource(id = R.drawable.close_circle),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "How can we help?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Stacked with accessing your Amalitech account or having another issues? ",
            style = TextStyle(
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {  }
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Icon(
                            tint = Color.DarkGray,
                            painter = painterResource(id = R.drawable.alarm),
                            contentDescription = "alarm"
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                        Text(text = "Report an issue", color = Color.DarkGray)
                    }
                }
                Icon(
                    tint = Color.DarkGray,
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "right_chevron",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
fun SucessBottomSheetContent(onClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Successful",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your report has been submitted successfully. You will be notified in your email with the progress. Cheers..",
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(primaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = onClick
        ) {
            Text(text = "Got it")
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSucessModalSheet() {
    var openSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    Box {
        Button(
            onClick = { openSheet = true },
            colors = ButtonDefaults.buttonColors(primaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
        ) {
            Text(text = "Submit report")
        }
    }

    if (openSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openSheet = false },
        ) {
            SucessBottomSheetContent(
                onClick = {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(onClick: () -> Unit, title: AuthenticationException? = null) {

    val sheetContent = BottomSheetContent(
        title = title?.message ?: "A Network Error Occurred",
        drawable = R.drawable.sad_face,
        content = "The internet connection appears to be offline." +
                "\nPlease check your internet connecting"
    )
    var openSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    if (openSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { openSheet = false },
            dragHandle = {}

        ) {
            Column {
                Image(
                    painter = painterResource(id = sheetContent.drawable),
                    contentDescription = "sad face",
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = sheetContent.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = sheetContent.content)
                Button(onClick = onClick) {
                    Text(text = "Try Again")
                }
            }
        }
    }
}