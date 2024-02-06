package com.amalitech.arms_mobile.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.ui.theme.primaryColor


@Preview(showBackground = true)
@Composable
fun FilledActionButton() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        ModalSheetM3(
            title = "Credentials provided are\nincorrect",
            drawableId = R.drawable.sad_face,
            subTitle = "Oops! your email and password " +
                    "combination\ndoes not match our records.",
            btnTitle = "Try again",
            mainBtnTitle = "Login"
        )
    }
}

@Composable
fun TextActionButton(text: String) {
    Text(
        modifier = Modifier.clickable {},
        text = text,
        textDecoration = TextDecoration.Underline,
        color = primaryColor
    )
}

@Composable
fun HelpActionButton() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.message_text),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Help")
    }
}