package com.amalitech.arms_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amalitech.arms_mobile.composables.FilledActionButton
import com.amalitech.arms_mobile.composables.HelpActionButton
import com.amalitech.arms_mobile.composables.ModalSheetM3
import com.amalitech.arms_mobile.composables.TextActionButton
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.amalitech.arms_mobile.ui.theme.primaryColor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ARMSMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginInScreen()
                }
            }
        }
    }
}

@Composable
fun LoginInScreen() {

    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 12.dp)
    )
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.amalitech_logo),
                    contentDescription = "arms logo",
                    modifier = Modifier.width(150.dp)
                )
                HelpActionButton()
            }
            Text(
                text = "Sign In",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )
            Text(
                text = "Welcome back. Enter your credentials to access " +
                        "your account",

                )
            Text(text = "Email address")
            OutlineBorderTextFormField(text = "Enter your email address")
            Row {
                Text(text = "Password")
                TextActionButton("Forgot Password")
            }
            OutlineBorderTextFormField("Enter Your Password")
            Row {
                Checkbox(checked = true, onCheckedChange = {})
                Text(text = "Keep me signed in")
            }
            FilledActionButton()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ARMSMobileTheme {
        LoginInScreen()
    }
}

@Composable
fun OutlineBorderTextFormField(text: String) {
    val textState = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = text) },
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = RoundedCornerShape(4.dp),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = ""
            )
        }

    )
}

