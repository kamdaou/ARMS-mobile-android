package com.amalitech.arms_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
//import com.amalitech.Mutation
//import com.apollographql.apollo3.ApolloClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()

//            val apolloClient = ApolloClient.Builder()
//                    .serverUrl("")
//                    .build()
//           val response = apolloClient.mutation(Mutation(data = UserData()))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
        MainApp()
}

@Composable
fun SurveyAnswer() {
    Row {
        Image(painterResource(id = R.drawable.spark), contentDescription = "")
    }
}