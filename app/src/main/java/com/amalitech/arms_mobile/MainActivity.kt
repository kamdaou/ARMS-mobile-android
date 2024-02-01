package com.amalitech.arms_mobile

import android.os.Bundle
import android.service.autofill.UserData
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.amalitech.Mutation
import com.amalitech.arms_mobile.ui.theme.ARMSMobileTheme
import com.apollographql.apollo3.ApolloClient

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
                    Greeting("Android")
                }
            }


            val apolloClient = ApolloClient.Builder()
                    .serverUrl("")
                    .build()
//            val response = apolloClient.mutation(Mutation(data = UserData()))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ARMSMobileTheme {
        Greeting("Android")
        SurveyAnswer()
    }
}

@Composable
fun SurveyAnswer() {
    Row {
        Image(painterResource(id = R.drawable.spark), contentDescription = "")
    }
}