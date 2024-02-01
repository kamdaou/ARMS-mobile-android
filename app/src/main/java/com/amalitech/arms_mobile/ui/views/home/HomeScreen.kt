package com.amalitech.arms_mobile.ui.views.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.amalitech.arms_mobile.core.AppRoute

object HomeScreenRoute : AppRoute {
    override val path = "home"
    override val title = 1
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(text = "HomeScreen")
        }
    }
}
