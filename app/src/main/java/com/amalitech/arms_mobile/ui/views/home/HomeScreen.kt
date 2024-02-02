package com.amalitech.arms_mobile.ui.views.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.core.interfaces.AppRoute

object HomeScreenRoute : AppRoute {
    override val path = "home"
    override val title = 1
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .background(color = Color(0xffFAFAFA))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large))
        ) {
            Row(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_small),
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_photo_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_extra_large_2))
                        .clickable { },
                )
                Spacer(
                    modifier = Modifier.weight(1F)
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = dimensionResource(id = R.dimen.padding_medium))
                        .size(dimensionResource(id = R.dimen.padding_large))
                        .clickable { },
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_notification),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = dimensionResource(id = R.dimen.padding_medium))
                        .size(dimensionResource(id = R.dimen.padding_large))
                        .clickable { },
                )
            }
            Text(
                text = "Good morning Precious",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_small)
                )
            )
            Text(
                text = "Here's what's going on today.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    bottom = dimensionResource(id = R.dimen.padding_small)
                )
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            HorizontalListBuilder<String>(title = "Who's out") { index, item ->
                Text(item)
            }
        }
    }
}
